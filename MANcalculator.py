# spacing 
print("\n")

# header
print("M.A.N. Calculator!")

# inputs
firstTerm = input("first term coefficient: ")
secondTerm = input("second term coefficient: ")
thirdTerm = input("third term coefficient: ")

try:
    # try to parse to int
    first = int(firstTerm)
    second = int(secondTerm)
    third = int(thirdTerm)

    # ma of man (looking for n)
    m = first * third  # what multiplies to
    a = second  # what adds to

    divisor = 1
    while True:
        result = m // divisor
        guess = result + divisor
        if guess == a:
            print(result)
            print(divisor)

            print("\n")
            break
        if divisor > m:
            print("Your trinomial has no result!")
            print("\n")        
            break

        divisor += 1

except ValueError:
    # if not int then print err
    print("Please enter an integer representing the coefficient for each term")
    print("\n")
