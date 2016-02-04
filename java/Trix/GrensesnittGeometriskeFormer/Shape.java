/**
* Grensesnittet <code>Shape</code> gir metoder for å
* beregne areal og omkrets.
*/
interface Shape {

    /**
    * Beregner en figurs areal.
    * @return figurens areal.
    */
    public double area();

    /**
    * Beregner en figurs omkrets.
    * @return figurens omkrets.
    */
    public double circumference();
}
