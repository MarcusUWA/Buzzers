const int seven = 14;
const int eight = 15;
const int nine = 16;
const int ten = 17;
const int eleven = 18;
const int twelve = 19;

const int one = 9;
const int two = 8;
const int three = 7;
const int four = 6;
const int five = 5;
const int six = 4;

const int reset = 2;

unsigned long firstTime = 0;
int order[12];
unsigned long times[12];
int resetState;
int iterator;
int change;

void setup() {
  // put your setup code here, to run once:
  pinMode(one, INPUT);
  pinMode(two, INPUT);
  pinMode(three, INPUT);
  pinMode(four, INPUT);
  pinMode(five, INPUT);
  pinMode(six, INPUT);
  pinMode(seven, INPUT);
  pinMode(eight, INPUT);
  pinMode(nine, INPUT);
  pinMode(ten, INPUT);
  pinMode(eleven, INPUT);
  pinMode(twelve, INPUT);

  pinMode(reset, INPUT);

  for(int i =0; i<12; i++) {
    order[i] = 13;
  }

  change = 0;
  iterator = 0;
  resetState = 1;
  
  Serial.begin(9600); 
  Serial.println("Starting...");
}

void loop() {

  if(digitalRead(reset) == HIGH) {
    if(resetState == 0) {
      Serial.println("Reset!!");
      resetState = 1;
      firstTime = 0;
      iterator = 0;
      for(int i = 0; i<12; i++) {
        order[i] = 13;
      }
    }
  }
  
  else {
    int value[12];

    value[0] = digitalRead(one);
    value[1] = digitalRead(two);
    value[2] = digitalRead(three);
    value[3] = digitalRead(four);
    value[4] = digitalRead(five);
    value[5] = digitalRead(six);
    value[6] = digitalRead(seven);
    value[7] = digitalRead(eight);
    value[8] = digitalRead(nine);
    value[9] = digitalRead(ten);
    value[10] = digitalRead(eleven);
    value[11] = digitalRead(twelve);

    if(resetState == 1) {
      for(int i = 0; i<12; i++) {
        if(value[i] == 1) {
          resetState = 0;
          change = 1;
          iterator++;
          order[i] = iterator;
          firstTime = micros();
          times[i] = 0;
        }
      }
    }
    else {
      for(int i = 0; i<12; i++) {
        if(order[i] != 13) {
          continue;
        }
        else {
          if(value[i] == 1) {
            change = 1;
            iterator++;
            order[i] = iterator;
            unsigned long currentTime = micros();
            times[i] = currentTime-firstTime;
          }
        }
      }
    }

    if(change == 1) {
      for(int i = 0; i<12; i++) {
        Serial.print(order[i]);
      
      if(order[i] != 13) {
        Serial.print(" ");
        Serial.print(times[i]/1000000);
        Serial.print(":");
        Serial.print((times[i]%1000000)/1000);
        Serial.print(":");
        Serial.print(times[i]%1000);
      }
      
        Serial.print(",");
      }
      Serial.println("");
    }
    change = 0;
  }
}
