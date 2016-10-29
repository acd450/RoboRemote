#include <Wire.h>
#include <Adafruit_MotorShield.h>
#include <SoftwareSerial.h>
#include "utility/Adafruit_MS_PWMServoDriver.h"

// Create the motor shield object with the default I2C address
Adafruit_MotorShield AFMS = Adafruit_MotorShield(); 
// Or, create it with a different I2C address (say for stacking)
// Adafruit_MotorShield AFMS = Adafruit_MotorShield(0x61); 

// Select which 'port' M1, M2, M3 or M4. In this case, M1
Adafruit_DCMotor *myMotor = AFMS.getMotor(1);
// You can also make another motor on port M2
Adafruit_DCMotor *myOtherMotor = AFMS.getMotor(2);

SoftwareSerial mySerial(10, 11); // RX, TX

void setup() {
  Serial.begin(9600);           // set up Serial library at 9600 bps
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }
  Serial.println("Adafruit Motorshield v2 - DC Motor test!");

  mySerial.begin(4800);
  mySerial.println("Hello, world?");
  
  AFMS.begin();  // create with the default frequency 1.6KHz
  //AFMS.begin(1000);  // OR with a different frequency, say 1KHz
  
  // Set the speed to start, from 0 (off) to 255 (max speed)
  
  myMotor->setSpeed(102);
  myOtherMotor->setSpeed(105);
  myMotor->run(FORWARD);
  myOtherMotor->run(FORWARD);
  // turn on motor
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);
  
  // turn on motor
  
}

void loop() {

  if (mySerial.available()) {
    char c = mySerial.read();
  }

  uint8_t i;
  
  Serial.print("tick");

//delay(5000);
//moveFlail();

if(c == '0'){
  move1Foot();
}
if(c == '1'){
  move2Foot();
}
if(c == '2'){
  move3Foot();
}






void move1Foot()
{
  myMotor->run(FORWARD);
  myOtherMotor->run(FORWARD);
  delay(800);

  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);
}

void move2Foot()
{
  myMotor->run(FORWARD);
  myOtherMotor->run(FORWARD);
  delay(1600);

  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);
}

void move3Foot()
{
  myMotor->run(FORWARD);
  myOtherMotor->run(FORWARD);
  delay(2400);

  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);
}

void move4Foot()
{
  myMotor->run(FORWARD);
  myOtherMotor->run(FORWARD);
  delay(3050);

  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);
}

void turnLeft()
{
  myMotor->run(BACKWARD);
  myOtherMotor->run(FORWARD);
  delay(260);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);
}

void turnRight()
{
  myMotor->run(FORWARD);
  myOtherMotor->run(BACKWARD);
  delay(260);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);
}

void arcRight()
{
  myMotor->setSpeed(125);
  myOtherMotor->setSpeed(80);
  myMotor->run(FORWARD);
  myOtherMotor->run(FORWARD);
  delay(265*4.5);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);
  myMotor->setSpeed(102);
  myOtherMotor->setSpeed(105);
}

void moveFlail()
{
  myMotor->setSpeed(255);
  myOtherMotor->setSpeed(255);
  
  myMotor->run(FORWARD);
  myOtherMotor->run(BACKWARD);
  delay(100);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);

  myOtherMotor->run(FORWARD);
  myMotor->run(BACKWARD);
  delay(100);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);

  myMotor->run(FORWARD);
  myOtherMotor->run(BACKWARD);
  delay(100);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);

   myOtherMotor->run(FORWARD);
  myMotor->run(BACKWARD);
  delay(100);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);

  myMotor->run(FORWARD);
  myOtherMotor->run(BACKWARD);
  delay(100);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);

   myOtherMotor->run(FORWARD);
  myMotor->run(BACKWARD);
  delay(100);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);

  myMotor->run(FORWARD);
  myOtherMotor->run(BACKWARD);
  delay(100);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);

   myOtherMotor->run(FORWARD);
  myMotor->run(BACKWARD);
  delay(100);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);

  myMotor->run(FORWARD);
  myOtherMotor->run(BACKWARD);
  delay(100);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);

   myOtherMotor->run(FORWARD);
  myMotor->run(BACKWARD);
  delay(100);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);

  myMotor->run(FORWARD);
  myOtherMotor->run(BACKWARD);
  delay(100);
  myMotor->run(RELEASE);
  myOtherMotor->run(RELEASE);


  myMotor->setSpeed(102);
  myOtherMotor->setSpeed(105);
}

