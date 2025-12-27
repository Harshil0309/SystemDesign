from datetime import date, timedelta
from enum import Enum

class CarType(Enum):
    SEDAN="SEDAN"
    SUV="SUV"
    HATCHBACK= "HATCHBACK"
    LUXURY="LUXURY"

class BookingStatus(Enum):
    BOOKED="BOOKED"
    CANCELLED="CANCELLED"
    COMPLETED="COMPLETED"

class User():
    def __init__(self,user_id:str,name:str,license_number:str):
        self.user_id=user_id
        self.name=name
        self.license_number=license_number

    def get_user_id(self):
        return self.user_id
    
class Car():
    def __init__(self,car_id:str,car_type:CarType,price_per_day:float):
        self.car_id=car_id
        self.car_type=car_type
        self.price_per_day=price_per_day
        self.is_available=True

    def mark_available(self):
        self.is_available=True

    def mark_unavailable(self):
        self.is_available=False

class Booking():
    def __init__(self,booking_id:str,user:User,car:Car,start_date:date,end_date:date):
        self.booking_id=booking_id
        self.user=user
        self.car=car
        self.start_date=start_date
        self.end_date=end_date
        self.status=BookingStatus.BOOKED
        self.total_price=self._calculate_price()

    def _calculate_price(self):
        total_cost=((self.end_date-self.start_date).days+1) * self.car.price_per_day
        return total_cost
    
    def cancel_booking(self):
        self.status=BookingStatus.CANCELLED
        self.car.mark_available()

    def complete_booking(self):
        self.status=BookingStatus.COMPLETED
        self.car.mark_available()

class Payment():
    def __init__(self,payment_id:str,amount:float):
        self.payment_id=payment_id
        self.amount=amount
        self.is_paid=False
    
    def make_payment(self):
        self.is_paid=True

class CarRentalSystem:
    def __init__(self):
        self.users={}
        self.cars={}
        self.bookings={}

    def add_user(self,user:User):
        self.users[user.get_user_id()]=user 
    
    
    def add_car(self,car:Car):
        self.cars[car.car_id]=car 
    

    def book_car(self,booking_id:str,user_id:str,car_id:str,start_date:date,end_date:date):
        if user_id not in self.users:
                raise Exception("User not found")

        if car_id not in self.cars:
            raise Exception("Car not found")
        
        car=self.cars[car_id]

        if not car.is_available:
            raise Exception("Car not available")
        
        car.mark_unavailable()
        booking=Booking(booking_id,self.users[user_id], car,
                start_date,
                end_date)
        
        self.bookings[booking_id]=booking
        return booking
        
    

if __name__ == "__main__":
    system = CarRentalSystem()

    user = User("U1", "Harshil Gupta", "DL-123456")
    car = Car("C1", CarType.SUV, 3000)

    system.add_user(user)
    system.add_car(car)

    booking = system.book_car(
        booking_id="B1",
        user_id="U1",
        car_id="C1",
        start_date=date.today(),
        end_date=date.today() + timedelta(days=2)
    )

    print("Booking Successful!")
    print("Total Price:", booking.total_price)

    # https://chatgpt.com/share/694fabbb-a780-8000-a0b7-f3b47e93c075
