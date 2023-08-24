import DAO.ClientCrudService;
import DAO.ClientDAO;
import exceptions.ArgumentException;
import model.Client;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


class ClientCrudServiceTest {
    private SessionFactory sessionFactory = HibernateUtilTest.getInstance().getSessionFactory();
    private ClientDAO clientDAO;
    @BeforeEach
    private void getClient() {
        clientDAO = new ClientCrudService(sessionFactory);
    }

    @Test
    void listAll_AddClient_GetRightCount() throws Exception {
        List<Client> initialClients = clientDAO.listAll();
        int initialNumberClients = initialClients.size();
        clientDAO.create("Sergey");
        List<Client> resultlClientsList = clientDAO.listAll();
        int resultCountClients = resultlClientsList.size();
        assertEquals(initialNumberClients + 1, resultCountClients);
    }

    @Test
    void deleteById_SetZeroArgument_ThrowException() {
        assertThrows(ArgumentException.class, () -> clientDAO.deleteById(0));
    }

    @Test
    void deleteById_LessZeroArgument_ThrowException() {
        assertThrows(ArgumentException.class, () -> clientDAO.deleteById(0));
    }

    @Test
    void deleteById_ArgumentMoreThenExistMembers_ThrowException() {
        assertThrows(ArgumentException.class, () -> clientDAO.deleteById(100000));
    }

    @Test
    void deleteById_ArgumentExistInBD_PositiveResult() throws Exception {
        long IdMemberForDelet = clientDAO.create("MemberForDelet");
        List<Client> initialClientsList = clientDAO.listAll();
        int initialNumberClients = initialClientsList.size();
        clientDAO.deleteById(IdMemberForDelet);
        List<Client> controlClientsList = clientDAO.listAll();
        int countClientsAfterDelete = controlClientsList.size();
        assertEquals(initialNumberClients - 1, countClientsAfterDelete);
    }

    @Test
    void setName_SetIdLessZero_ThrowException() {
        assertThrows(ArgumentException.class, () -> clientDAO.setName(-2, "Hanna"));
    }

    @Test
    void setName_SetIdZeroNameValid_ThrowException() {
        assertThrows(ArgumentException.class, () -> clientDAO.setName(0, "Hanna"));
    }


    @Test
    void setName_SetIdDoesNotExistNameValid_ThrowException() throws Exception {
        long maxId = clientDAO.create("Mariy");
        assertThrows(ArgumentException.class, () -> clientDAO.setName(maxId + 1, "Hanna"));
    }


    @Test
    void setName_SetIdValidNameNull_ThrowException() throws Exception {
        long maxId = clientDAO.create("Mariy");
        assertThrows(ArgumentException.class, () -> clientDAO.setName(maxId, null));
    }


    @Test
    void setName_SetIdValidNameMore200Simbols_ThrowException() throws Exception {
        StringBuilder testName = new StringBuilder();
        for (int i = 0; i < 201; i++) {
            testName.append("a");
        }
        long maxId = clientDAO.create("Mariy");
        assertThrows(ArgumentException.class, () -> clientDAO.setName(maxId, testName.toString()));
    }

    @Test
    void setName_SetIdValidNameLess2Simbols_ThrowException() throws Exception {
        long maxId = clientDAO.create("Mariy");
        assertThrows(ArgumentException.class, () -> clientDAO.setName(maxId, "h"));
    }

    @Test
    void setName_SetIdValidNameValid_PositiveResult() throws Exception {
        String name = "Mariy";
        String changedName = "Anton";
        long maxId = clientDAO.create(name);
        clientDAO.setName(maxId, changedName);
        assertEquals(changedName, clientDAO.getById(maxId));
    }


    @Test
    void create_SetNameLessTwoSimbols_ThrowException() {
        assertThrows(ArgumentException.class, () -> clientDAO.create("a"));
    }

    @Test
    void create_SetNameMore200Simbols_ThrowException() {
        StringBuilder testName = new StringBuilder();
        for (int i = 0; i < 201; i++) {
            testName.append("a");
        }
        assertThrows(ArgumentException.class, () -> clientDAO.create(testName.toString()));
    }

    @Test
    void create_SetNameValid_PositiveResult() throws Exception {
        String name = "newMember";
        List<Client> initialClientsList = clientDAO.listAll();
        long idNewMember = clientDAO.create(name);
        String clientFromDB = clientDAO.getById(idNewMember);
        assertEquals(name, clientFromDB);
    }

    @Test
    void create_SetNameNull_ThrowException() {
        assertThrows(ArgumentException.class, () -> clientDAO.create(null));
    }


    @Test
    void getById_SetZeroArgument_ThrowException() {
        assertThrows(ArgumentException.class, () -> clientDAO.getById(0));

    }

    @Test
    void getById_LessZeroArgument_ThrowException() {
        assertThrows(ArgumentException.class, () -> clientDAO.getById(-1));
    }

    @Test
    void getById_ArgumentMoreThenExist_ThrowException() {
        List<Long> idList = clientDAO.listAll().stream().map(client -> client.getId()).collect(Collectors.toList());
        Long idMAx = 0L;
        for (Long idCliet : idList) {
            if (idMAx < idCliet) {
                idMAx = idCliet;
            }
        }
        long idMoreThenExist = idMAx + 1L;
        assertThrows(ArgumentException.class, () -> clientDAO.deleteById(idMoreThenExist));
    }

    @Test
    void getById_IdValid_PositiveResult() throws Exception {
        List<Long> idList = clientDAO.listAll().stream().map(client -> client.getId()).collect(Collectors.toList());

        assertNotEquals(null, clientDAO.getById(idList.get(1)));
    }

}