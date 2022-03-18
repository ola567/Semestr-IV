import Foundation

//Exercises1

func minValue (_ a: Int, _ b: Int) -> Int 
{
	if a > b
	{
	    return b
	}
	else
	{
	    return a
	}
}
print("Min value: \(minValue(5,8))")

func lastDigit(_ a: Int) -> Int 
{
	let lastDigit = a % 10
	return lastDigit
}
print("Last digit: \(lastDigit(22))")

func divides(_ a: Int, _ b: Int) -> Bool 
{
	if ( a % b == 0)
	{
	    return true
	}
	else
	{
	    return false
	}
}
print("If a divides b: \(divides(5,2))")
print("If a divides b: \(divides(24,6))")

func countDivisors(_ a: Int) -> Int
{
    var counter = 0
    for i in 1...a
    {
        if ( divides(a, i))
        {
            counter = counter + 1
        }
    }
    return counter
}
print("Numer of divisors: \(countDivisors(8))")
print("Numer of divisors: \(countDivisors(24))")

func isPrime(_ a: Int) -> Bool
{
    if(countDivisors(a) == 2)
    {
        return true
    }
    else
    {
        return false
    }
}
print("If prime: \(isPrime(21))")
print("If prime: \(isPrime(2))")

//Exercises2

func smartBart(_ n: Int,_ tekst: () -> ())
{
    for _ in 1...n{
        tekst()
    }
}
var bart: ()->() = {
    print("I will pass this course with best mark, because Swift is great!")
}
smartBart(3, bart)

let numbers = [10,16,18,30,38,40,44,50]
print(numbers.filter{$0%4 == 0})
print(numbers.reduce(Int.min, {max($0, $1)}))

var strings = ["Gdansk", "University", "of", "Technology"]
print(strings.reduce(""){$0 + " " + $1})

let numbers1 = [1,2,3,4,5,6]
print(((numbers1.map{$0 * $0}).filter{$0%2 == 1}).reduce(0){$0 + $1})

//Exercises3

func minmax(_ a: Int, _ b: Int) -> (Int, Int)
{
    return (a, b)
}
print(minmax(2,1))

var stringsArray = ["gdansk", "university", "gdansk", "university", "university", "of",   "technology", "technology", "gdansk", "gdansk"]

var countedStrings: [(String, Int)] = []
var wasString = 0

for str in stringsArray
{
    for i in 0..<countedStrings.count
    {
        if countedStrings[i].0 == str
        {
            countedStrings[i].1+=1
            wasString = 1
        }
    }
    if wasString == 0 {
        countedStrings.append((str, 1))
    }
    
    wasString = 0
}
print("Array: \(countedStrings)")

//Exercises4

enum Day: String 
{
    case Monday
    case Tuesday
    case Wednesday
    case Thursday
    case Friday
    case Saturday
    case Sunday
    
    func emojiForDay () -> String 
    {
        switch self 
        {
        case .Monday:
            return "🙂"
        case .Tuesday:
            return "😢"
        case .Wednesday:
            return "😕"
        case .Thursday:
            return "😌"
        case .Friday:
            return "🥳"
        case .Saturday:
            return "🥴"
        case .Sunday:
            return "😀"
        }
    }
}

let day = Day.Friday
print(day.emojiForDay())