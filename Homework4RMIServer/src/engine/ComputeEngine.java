/**
 * 
 */
package engine;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import compute.Compute;
import compute.Task;

/**
 * @author pzoli
 *
 */
public class ComputeEngine implements Compute {
    
    public ComputeEngine() {
        super();
    }

    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        return t.execute();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
   /*     
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
            System.setProperty("java.security.policy","file:///server.policy");
        }
   */      
        try {
            String name = "Compute";
            Compute engine = new ComputeEngine();
            Compute stub =
                (Compute) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry("localhost",1099);
            registry.rebind(name, stub);
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }

}
