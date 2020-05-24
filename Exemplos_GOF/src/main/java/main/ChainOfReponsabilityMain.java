package main;

import pattern.chainresponsability.DirectorPPower;
import pattern.chainresponsability.ManagerPPower;
import pattern.chainresponsability.PresidentPPower;
import pattern.chainresponsability.PurchaseRequest;
import pattern.chainresponsability.VicePresidentPPower;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChainOfReponsabilityMain {

	public static void main(String[] args) {
		ManagerPPower manager = new ManagerPPower();
		DirectorPPower director = new DirectorPPower();
		VicePresidentPPower vp = new VicePresidentPPower();
		PresidentPPower president = new PresidentPPower();
		manager.setSuccessor(director);
		director.setSuccessor(vp);
		vp.setSuccessor(president);

		// Press Ctrl+C to end.
		try
		{
			while (true)
			{
				System.out.println("Insira o valor para verificar quem tem permissão para aprovar sua requisição");
				System.out.print("-->");
				double d = Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
				manager.processRequest(new PurchaseRequest(d, "Geral"));
			}
		} catch (Exception e)
		{
			System.exit(1);
		}
	}
}
