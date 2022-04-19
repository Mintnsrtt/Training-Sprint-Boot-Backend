package com.iamnsrt.training.backend;

import com.iamnsrt.training.backend.entity.Address;
import com.iamnsrt.training.backend.entity.Social;
import com.iamnsrt.training.backend.entity.User;
import com.iamnsrt.training.backend.exception.BaseException;
import com.iamnsrt.training.backend.exception.UserException;
import com.iamnsrt.training.backend.service.AddressService;
import com.iamnsrt.training.backend.service.SocialService;
import com.iamnsrt.training.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;

	@Autowired
	private SocialService socialService;

	@Autowired
	private AddressService addressService;

	interface TestCreateData {
		String email = "mumu@hamster.com";
		String password = "12345678";
		String name = "MuHamster";
	}

	interface TestUpdateData {
		String name = "Hamster";
	}

	interface SocialTestCreateData {
		String facebook = "MuHamHamFacebook";
		String line = "MuHamHamLine";
		String instagram = "MuHamHamInstagram";
		String tiktok = "MuHamHamTiktok";
	}

	interface AddressTestCreateData {
		String line1 = "17/10";
		String line2 = "Punnawiti";
		String zipCode = "10260";
	}

	interface AddressTestCreateData2 {
		String line1 = "13/11";
		String line2 = "Kalasin";
		String zipCode = "46160";
	}

	@Order(1)
	@Test
	void testCreate() throws BaseException {

		User user = userService.create(
				TestCreateData.email,
				TestCreateData.password,
				TestCreateData.name
		);

		// check not null
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());

		// check Equals
		Assertions.assertEquals(TestCreateData.email, user.getEmail());

		boolean isMatched = userService.matchPassword(TestCreateData.password, user.getPassword());
		Assertions.assertTrue(isMatched);

		Assertions.assertEquals(TestCreateData.name, user.getName());
	}

	@Order(2)
	@Test
	void testUpdate() throws BaseException {
		Optional<User> opt =  userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		User updatedUser = userService.updateName(user.getId(), TestUpdateData.name);

		Assertions.assertNotNull(updatedUser);
		Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());
	}

	@Order(3)
	@Test
	void testCreateSocial() throws UserException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		Social social = user.getSocial();
		Assertions.assertNull(social);

		social = socialService.create(
				user,
				SocialTestCreateData.facebook,
				SocialTestCreateData.line,
				SocialTestCreateData.instagram,
				SocialTestCreateData.tiktok
		);

		Assertions.assertNotNull(social);
		Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());
	}
	@Order(4)
	@Test
	void testCreateAddress() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		List<Address> addresses = user.getAddresses();

		Assertions.assertTrue(addresses.isEmpty());
		createAddress(user, AddressTestCreateData.line1, AddressTestCreateData.line2, AddressTestCreateData.zipCode);
		createAddress(user, AddressTestCreateData2.line1, AddressTestCreateData2.line2, AddressTestCreateData2.zipCode);
	}

	private void createAddress(User user, String line1, String line2, String zipCode) {
		Address address = addressService.create(
				user, line1, line2, zipCode);

		Assertions.assertNotNull(address);
		Assertions.assertEquals(line1, address.getLine1());
		Assertions.assertEquals(line2, address.getLine2());
		Assertions.assertEquals(zipCode, address.getZipCode());
	}


	@Order(6)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		//check Social
		Social social = user.getSocial();
		Assertions.assertNotNull(social);
		Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());

		//check Address
		List<Address> addresses = user.getAddresses();
		Assertions.assertFalse(addresses.isEmpty());
		Assertions.assertEquals(2, addresses.size());

		userService.deleteById(user.getId());

		Optional<User> optCheckDelete = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(optCheckDelete.isEmpty());
	}
}
