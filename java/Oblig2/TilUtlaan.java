/** Objektene av alle klassene som implementerer dette grensesnittet tilordner
 * objektet en String om objetet laanes ut, og fjernes den naar den leveres 
 * tilbake.
 * @author Eva H. Fritzell
 * versjon 5. februar 2016
 */

interface TilUtlaan {
    /**
     * Tilordner objektet en String (som definerer laaneren), men bare hvis 
     * objektet ikke er tilordnet en String fra foer.
     *
     *@param laaner  navnet paa laaneren av objektet.
     */
    public void laanUt(String laaner);

    /**
     * Fjerner den tidligere tilordnede strengen slik at objektet ikke lenger
     * har en laaner.
     */
    public void leverTilbake();
}
