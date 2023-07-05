package practice;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Car
{
String vin, description;
public boolean equals(Object other) // Depends on vin only
{
if (!(other instanceof Car))
return false;
else
return vin.equalsIgnoreCase(((Car)other).vin);
}
public int hashCode() { return vin.hashCode();} // Depends on vin only
public Car(String v, String d) { vin = v; description = d; }
public String toString() { return vin + " " + description; }


public static void main(String [ ] args)
{
Set<Car> carSet = new HashSet<Car>();
Car [ ] myRides = {
new Car("TJ1", "Toyota"),
new Car("GM1", "Corvette"),
new Car("TJ1", "Toyota Corolla"),
new Car("tj1","Toyoya Corolla")
};


if(myRides[0].equals(myRides[3])) {
	System.out.println("equal");
}

// Add the cars to the HashSet
for (Car c : myRides)
carSet.add(c);
// Print the list using an Iterator
Iterator it = carSet.iterator();
while (it.hasNext())
System.out.println(it.next());
} 
}