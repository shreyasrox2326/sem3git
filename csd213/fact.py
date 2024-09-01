principal = 10000
rateOfInterest = 3
numYears = 3
import math as Math

print(Math.pow((1 + (rateOfInterest / 100)),numYears*12))
totalAmt = principal * Math.pow((1 + (rateOfInterest / 100)),numYears*12)
print(totalAmt/(12 * numYears))
monthlyPayment = (principal * (rateOfInterest / 100))/(1-(1/(Math.pow((1 + (rateOfInterest / 100)),(numYears*12)))))
print(monthlyPayment)