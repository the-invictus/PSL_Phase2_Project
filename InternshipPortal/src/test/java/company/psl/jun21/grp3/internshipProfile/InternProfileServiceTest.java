package company.psl.jun21.grp3.internshipProfile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.psl.jun21.grp3.company.Company;
import com.psl.jun21.grp3.company.CompanyRepository;
import com.psl.jun21.grp3.company.CompanyService;
import com.psl.jun21.grp3.internshipprofile.InternshipProfile;
import com.psl.jun21.grp3.internshipprofile.InternshipProfileMvcController;
import com.psl.jun21.grp3.internshipprofile.InternshipProfileRepository;
import com.psl.jun21.grp3.internshipprofile.InternshipProfileService;
import com.psl.jun21.grp3.internshipprofile.RecordNotFoundException;
import com.psl.jun21.grp3.user.User;
import com.psl.jun21.grp3.user.UserRepository;
import com.psl.jun21.grp3.user.UserRole;
import com.psl.jun21.grp3.user.UserServiceImpl;
@ExtendWith(MockitoExtension.class)
public class InternProfileServiceTest {
	/*
	@Autowired
	   private MockMvc mvc;

	   @MockBean
	   private InternshipProfileMvcController arrivalController;
	@Autowired
	InternshipProfileRepository repository;
	  @Autowired
	  private CompanyRepository companyRepository;

	  @Autowired
	  private UserRepository userRepository;
	  */
	/*
	@Autowired
	InternshipProfileService userService;

	@Autowired
	InternshipProfileRepository userRepository;
	*/
	 @Mock
	  private CompanyRepository companyRepository;
	 @InjectMocks
		InternshipProfileService userService;

		@Mock
		InternshipProfileRepository userRepository;
		private InternshipProfile existingEmployee;
		@Test
		public void testSave() {
			InternshipProfile prof=new InternshipProfile();
			prof.setId(23L);
			prof.setDescription("Very Good Internship");
			prof.setDomain("Computer Enginnering");
			prof.setDuration(200);
			/*
			when(companyRepository.findById(1L).get()).thenReturn(new Company());
			Company c1=companyRepository.findById(1L).get();
			*/
			Company c1=new Company();
			c1.setId(1L);
			prof.setCompany(c1);

			when(userRepository.save(ArgumentMatchers.any(InternshipProfile.class))).thenReturn(prof);
			InternshipProfile created = userService.createOrUpdateInternshipProfile(prof);
			assertThat(created.getDescription()).isSameAs(prof.getDescription());
		}
		@Test
		public void testGetAllProfiles() {
			/*
			InternshipProfile prof1=new InternshipProfile();
			prof1.setId(24L);
			prof1.setDescription("Very Good Internship");
			prof1.setDomain("Computer Enginnering");
			prof1.setDuration(200);
			
			Company c1=new Company();
			c1.setId(1L);
			prof1.setCompany(c1);
			InternshipProfile prof2=new InternshipProfile();
			prof2.setId(25L);
			prof2.setDescription("Very Good Internship");
			prof2.setDomain("Computer Enginnering");
			prof2.setDuration(200);
			prof2.setCompany(c1);
			InternshipProfile prof3=new InternshipProfile();
			prof3.setId(26L);
			prof3.setDescription("Very Good Internship");
			prof3.setDomain("Computer Enginnering");
			prof3.setDuration(200);
			prof3.setCompany(c1);
			List<InternshipProfile> l1=new ArrayList<>();
			l1.add(prof1);
			l1.add(prof2);
			l1.add(prof3);
			 when(userRepository.findAll()).thenReturn(l1);
			assertEquals(l1.size(),3);
			*/
			/*
			 Iterable<InternshipProfile> employees = userRepository.findAll();
			 int size=0;
			 Iterator<InternshipProfile> i1=employees.iterator();
			 while(i1.hasNext())
				 size++;
			 assertTrue(size>0);
			 */
			 when(userRepository.findAll()).thenReturn(Arrays.asList(new InternshipProfile()));
		        //When
		        List<InternshipProfile> userDtoList= userService.getAllInternshipProfiles();

		        //Then
		        assertTrue(userDtoList.size()>=1);
		        //verify(userRepository,times(1)).findAll();
			/*
			Iterable<InternshipProfile> iterable = new Iterable<InternshipProfile>() {

				@Override
				public Iterator<InternshipProfile> iterator() {
					List<InternshipProfile> users = new ArrayList<>(Arrays.asList(new InternshipProfile(), new InternshipProfile()));
					return users.iterator();
				}
			};
			when(userRepository.findAll()).thenReturn(iterable);
			assertThat(userService.getAllInternshipProfiles().size()).isEqualTo(2);
			*/
		}
/*
	@Test
	public void testCreate () {
		InternshipProfile prof=new InternshipProfile();
		
		prof.setDescription("Very Good Internship");
		prof.setDomain("Computer Enginnering");
		prof.setDuration(200);
		List<Company> c=(List<Company>) companyRepository.findAll();
		System.out.println(c.get(0));
		prof.setCompany(c.get(0));
		
		
		when(userRepository.save(ArgumentMatchers.any(InternshipProfile.class))).thenReturn(prof);
		InternshipProfile created = userService.createOrUpdateInternshipProfile(prof);
		assertThat(created.getDescription()).isSameAs(prof.getDescription());
		
	}
	*/
		@Test
		public void testDeleteObject() throws RecordNotFoundException {
			userRepository.deleteById(1L);
			assertEquals(userService.getAllInternshipProfiles().size(),0);
		}
		
		
}
