from math import floor
print(floor(16.66))  # expected output: 16
print(floor(-19.2))  # expected output: 20

print(floor(11))  # expected output: 11
print(floor(-33))  # expected output: -33

print(floor(True))  # expected output: 1
print(floor(False))  #expected output: 0

#print(floor(float("Infinity")))  # expected output: OverflowError: Cannot convert float infinity to integer
#print(floor(float("-Infinity")))  # expected output: OverflowError: Cannot convert float infinity to integer

#print(floor(float("nan")))  #  expected output: ValueError: Cannot convert float NaN to integer

#print(floor("str"))  # expected output: TypeError: A float is required, not: str

#print(floor(None))  # expected output: TypeError: A float is required, not: NoneType
