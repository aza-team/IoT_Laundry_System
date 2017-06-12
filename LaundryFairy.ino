/*
      ********************* 정 의 ********************* *******************  Pin 번호  ******************
      *        장비 번호는 변수명 number 이다.        * *            전류센서 pin 번호 : A2             *
      *   릴레이(전원)의 boolean형 변수 power 이다.   * *             릴레이 pin 번호 : 10              *
      ************************************************* *************************************************
*/

#include <Adafruit_CC3000.h>
#include <CC3000_MDNS.h>
#include <aREST.h>
#include <ccspi.h>
#include <SPI.h>
#include <string.h>
#include "utility/debug.h"

#define ADAFRUIT_CC3000_IRQ   7
#define ADAFRUIT_CC3000_VBAT  5
#define ADAFRUIT_CC3000_CS    10

Adafruit_CC3000 cc3000 = Adafruit_CC3000(ADAFRUIT_CC3000_CS, ADAFRUIT_CC3000_IRQ, ADAFRUIT_CC3000_VBAT,
                                         SPI_CLOCK_DIVIDER); // Wifi 모듈 셋팅

#define WLAN_SSID       "MROOM"
#define WLAN_PASS       "dpackddlstod"
#define WLAN_SECURITY   WLAN_SEC_WPA2
#define LISTEN_PORT 80

/* ip 수동 할당 */
/*
uint32_t ipAddress = cc3000.IP2U32(172, 20, 10, 4);
uint32_t netMask = cc3000.IP2U32(255, 255, 255, 240);
uint32_t defaultGateway = cc3000.IP2U32(172, 20, 10, 1);
uint32_t dns = cc3000.IP2U32(172, 20, 10, 1);
*/
#define ACTectionRange 20;  //전류센서 규격

Adafruit_CC3000_Server restServer(LISTEN_PORT);
MDNSResponder mdns;
aREST rest = aREST();

const int number = 1, ACPin = A2, RePin = 6, LEDPin = 13;
boolean power;
float Vref = 0;
uint32_t ip;

void setup()
{
  Serial.begin(115200);
  pinMode(RePin, OUTPUT);
  pinMode(LEDPin, OUTPUT);
  
  rest.set_name("relay_control");
  rest.set_id("1");
  
  Vref = readVref();
  if (!cc3000.begin())
    while(1);
/*  
  if (!cc3000.setStaticIPAddress(ipAddress, netMask, defaultGateway, dns))
    while (1);
*/
/*
  if (!cc3000.setDHCP())  // DHCP 초기화
    while(1);
*/ 
  if (!cc3000.connectToAP(WLAN_SSID, WLAN_PASS, WLAN_SECURITY))  // 공유기 접속
    while(1);

  while (!cc3000.checkDHCP())
   delay(100);
  
  // 공유기에 접속된 IP 출력
  while (! displayConnectionIp())
   delay(1000);

  if (!mdns.begin("arduino", cc3000))
    while(1); 
    
  // Start server
  restServer.begin();
  Serial.println(F("Listening for connections..."));
  digitalWrite(LEDPin, LOW);
}

void loop() {
  // Handle any multicast DNS requests
  mdns.update();

  // 전원이 ON 상태 일때
  if(digitalRead(LEDPin)) {
    digitalWrite(RePin, HIGH);
    float ACCurrentValue = readACCurrentValue(); //현재 전류값
    Serial.println(ACCurrentValue);
    delay(50);
  }
  //전원이 OFF 상태 일때
  else {
    digitalWrite(RePin, LOW);
  }
  Adafruit_CC3000_ClientRef client = restServer.available();
  rest.handle(client);
  }


float readACCurrentValue()
{
  float ACCurrtntValue = 0;
  unsigned int peakVoltage = 0;  
  unsigned int voltageVirtualValue = 0;  //Vrms
  for (int i = 0; i < 5; i++)
  {
    peakVoltage += analogRead(ACPin);   //피크 전압
    delay(1);
  }
  peakVoltage = peakVoltage / 5;   
  voltageVirtualValue = peakVoltage * 0.707;
  
  voltageVirtualValue = (voltageVirtualValue * Vref / 1024) / 2;  
  
  ACCurrtntValue = voltageVirtualValue * ACTectionRange;
  
  return ACCurrtntValue/1000;
}


/* 기준 전압 */
long readVref() 
{
  long result;
#if defined(__AVR_ATmega168__) || defined(__AVR_ATmega328__) || defined (__AVR_ATmega328P__)
  ADMUX = _BV(REFS0) | _BV(MUX3) | _BV(MUX2) | _BV(MUX1);
#elif defined(__AVR_ATmega32U4__) || defined(__AVR_ATmega1280__) || defined(__AVR_ATmega2560__) || defined(__AVR_AT90USB1286__)
  ADMUX = _BV(REFS0) | _BV(MUX4) | _BV(MUX3) | _BV(MUX2) | _BV(MUX1);
  ADCSRB &= ~_BV(MUX5);
#elif defined (__AVR_ATtiny24__) || defined(__AVR_ATtiny44__) || defined(__AVR_ATtiny84__)
  ADMUX = _BV(MUX5) | _BV(MUX0);
#elif defined (__AVR_ATtiny25__) || defined(__AVR_ATtiny45__) || defined(__AVR_ATtiny85__)
  ADMUX = _BV(MUX3) | _BV(MUX2);
#endif
#if defined(__AVR__)
  delay(2);
  ADCSRA |= _BV(ADSC);
  while (bit_is_set(ADCSRA, ADSC));
  result = ADCL;
  result |= ADCH << 8;
  result = 1126400L / result;
  return result;
#elif defined(__arm__)
  return (3300);
#else
  return (3300);
#endif
}

/* 할당 받은 ip 출력 */
bool displayConnectionIp(void)
{
  uint32_t ipAddress, netmask, gateway, dhcpserv, dnsserv;

  if(!cc3000.getIPAddress(&ipAddress, &netmask, &gateway, &dhcpserv, &dnsserv))
  {
   Serial.println(F("Unable to retrieve the IP Address!\r\n"));
   return false;
  }

  else
  {
    Serial.print(F("\nIP Addr: ")); cc3000.printIPdotsRev(ipAddress);
    Serial.print(F("\nNetmask: ")); cc3000.printIPdotsRev(netmask);
    Serial.print(F("\nGateway: ")); cc3000.printIPdotsRev(gateway);
    Serial.print(F("\nDHCPsrv: ")); cc3000.printIPdotsRev(dhcpserv);
    Serial.print(F("\nDNSserv: ")); cc3000.printIPdotsRev(dnsserv);
    Serial.println();
    return true;
  }
}
