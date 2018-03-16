/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start;

/**
 *
 * @author Przemek
 */
public final class MemoryTest implements Runnable {

    public MemoryTest() {
        run();
    }

    public void main() {
        System.out.println("Used Memory   : " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + " bytes");
        System.out.println("Free Memory   : " + Runtime.getRuntime().freeMemory() + " bytes");
        System.out.println("Total Memory  : " + Runtime.getRuntime().totalMemory() + " bytes");
        System.out.println("Max Memory    : " + Runtime.getRuntime().maxMemory() + " bytes");
        System.out.println("\n");
    }

    @Override
    public void run() {
        int i = 1;
        System.out.println("Max Memory    : " + Runtime.getRuntime().maxMemory() + " bytes\t" +((Runtime.getRuntime().maxMemory())/(1024*1024))+"Mb");

        while (true) {
            System.out.println(i + "\t Used Memory   : " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + " bytes \t"
                    + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024)) + "Mb");

            try {
                Thread.sleep(1000);
                i++;
            } catch (InterruptedException ex) {

            }
        }
    }
}
