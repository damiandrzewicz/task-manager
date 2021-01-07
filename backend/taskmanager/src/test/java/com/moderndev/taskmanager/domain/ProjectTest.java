package com.moderndev.taskmanager.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
	
	Project p;

	@BeforeEach
	void setUp() {
		p = new Project();
	}

	@Test
	void testSetName() {
		p.setName("testname");
		assertEquals("testname", p.getName());
	}
	
	@Test
	void testSetName_shouldTrim() {
		p.setName("    testname        ");
		assertEquals("testname", p.getName());
	}

	@Test
	void testSetDescription() {
		p.setDescription("testdescription");
		assertEquals("testdescription", p.getDescription());
	}
	
	@Test
	void testSetDescription_shouldTrim() {
		p.setDescription("    testdescription   ");
		assertEquals("testdescription", p.getDescription());
	}


	@Test
	void testToString() {
		assertNotNull(p.toString());
	}

	@Test
	void testBuilder() {
		var p = Project.builder().name("testname").description("testdescription").build();
		assertNotNull(p);
		assertNull(p.getId());
		assertNull(p.getCreated());
		assertNull(p.getParent());
		assertEquals("testname", p.getName());
		assertEquals("testdescription", p.getDescription());
	}

	@Test
	void testSetParent() {
		var parent = Project.builder().name("parent").build();
		assertNull(p.getParent());

		p.setParent(parent);

		assertEquals(parent, p.getParent());
		assertEquals("parent", p.getParent().getName());
		assertTrue(parent.getSubProjects().contains(p));
	}

	@Test
	void testProject() {
		assertNotNull(p);
	}

}
