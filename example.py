num1 = float(input("Enter the first number: "))

operator = input("Enter an operator (+, -, *, /): ")

num2 = float(input("Enter the second number: "))

if operator == "+":
    result = num1 + num2
elif operator == "-":
    result = num1 - num2
elif operator == "*":
    result = num1 * num2
elif operator == "/":
    result = num1 / num2
else:
    print("Invalid operator!")
    exit()

print("The result is:", result)