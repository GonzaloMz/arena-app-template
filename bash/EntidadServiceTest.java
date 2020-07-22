/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arena.backend.service;

import arena.backend.model.AbstractEntity;
import arena.backend.model.EntityUpdater;
import arena.backend.model.Entidad;
import arena.backend.repository.EntidadRepository;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author gonzalo
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EntidadServiceTest {
	
	@Autowired
	EntidadService entidadService;
	
	@Autowired
	EntidadRepository entidadRepository;
	
	private ArenaServiceTest<Entidad> tester;
	
	
	public class EntidadUpdater implements EntityUpdater{

		@Override
		public void update(AbstractEntity ent) {
		}

		@Override
		public void verify(AbstractEntity ent) {
		}
	
	}
	
	public EntidadServiceTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
		
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		this.tester = new ArenaServiceTest<Entidad>(entidadRepository, entidadService, new EntidadTitleUpdater());
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void saveAndGetAllTest(){
		this.tester.serviceCreateGetAllTest();
	}
	
	@Test
	public void updateAndGetTest(){
		this.tester.serviceUpdateGetTest();
	}
	
}

