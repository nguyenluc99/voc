from math import floor, ceil
print(floor(16.66))  # expected output: 16
print(floor(-19.2))  # expected output: 20

print(ceil(16.66))  # expected output: 17
print(ceil(-19.2))  # expected output: 20
#print(floor(float("Infinity")))  # expected output: OverflowError: Python cannot convert float infinity to integer
#print(floor(float("-Infinity")))  # expected output: OverflowError: Python cannot convert float infinity to integer

#print(floor(float("nan")))  #  expected output: ValueError: Python cannot convert float NaN to integer
