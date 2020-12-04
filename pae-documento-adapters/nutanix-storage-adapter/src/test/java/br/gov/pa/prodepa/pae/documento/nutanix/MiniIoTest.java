package br.gov.pa.prodepa.pae.documento.nutanix;

import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import br.gov.pa.prodepa.pae.documento.domain.NutanixClient;

public class MiniIoTest {

	String file_path = "/home/antoniojunior/Pictures/brasao_para.png";

	NutanixClient client = new NutanixClient();

	@Test
	public void putObjectTest() throws Exception {
		byte[] bytes = Files.readAllBytes(Paths.get(file_path));

		String id = client.putObject(bytes, "image/png");
		System.out.println("Successfully uploaded with id " + id);
		client.deleteObject(id);
	}

	@Test
	public void getObjectsTest() throws Exception {
		byte[] bytes = Files.readAllBytes(Paths.get(file_path));
		String id = client.putObject(bytes, "image/png");

		byte[] object = client.getObject(id);
		assertTrue(object != null && object.length > 0);

		client.deleteObject(id);
	}
}
