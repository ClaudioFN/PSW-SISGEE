package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.ConvenioDAO;
import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Serviços de alunos. Trata a lógica de negócios associada com a entidade
 * Convênio.
 *
 * @author Lucas Lima
 * @since 1.0
 */
public class ConvenioServices {

    /**
     * Recupera todos os convênios e retorna em uma lista.
     *
     * @return lista com todos os alunos
     */
    public static List<Convenio> listarConvenios() {
        GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
        return convenioDao.buscarTodos();
    }

    public static List<Convenio> listarConveniosVencer() {

        GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);

        Calendar cal = GregorianCalendar.getInstance();
        List<Convenio> x = convenioDao.buscarTodos();
        List<Convenio> aVencer = null;
        for (Convenio convenio : x) {
            String dataAssinou = convenio.getDataAssinatura();
            int anoAntigo = Integer.parseInt(dataAssinou.substring(6, dataAssinou.length()));
            int mesAntigo = Integer.parseInt(dataAssinou.substring(3, 5));

            
            int anoAtual = cal.get(Calendar.YEAR);
            int mesAtual = cal.get(Calendar.MONTH);

            int venceu = (anoAtual - anoAntigo) * 12 + (mesAtual - mesAntigo);
            if (venceu >= 58) {
                aVencer.add(convenio);

            }

        }
        return aVencer;

    }

    public static Convenio buscarConvenio(Convenio convenio) {
        GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
        return convenioDao.buscar(convenio.getIdConvenio());
    }

    public static void incluirConvenio(Convenio convenio) {
        GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
        PersistenceManager.getTransaction().begin();
        try {
            convenioDao.incluir(convenio);
            PersistenceManager.getTransaction().commit();
        } catch (Exception e) {
            PersistenceManager.getTransaction().rollback();
        }
    }

    public static Convenio buscarConvenioByNumeroEmpresa(String numero, Empresa emp) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            Convenio c = convenioDao.buscarByNumeroEmpresa(numero, emp);
            return c;
        } catch (Exception e) {
            return null;
        }
    }

    public static Convenio buscarConvenioByNumeroConvenio(String numero) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            System.out.println("ENTROU NO BUSCARCOONVENIO DO SERVICE ");
            Convenio a = convenioDao.buscarByNumero(numero);
            return a;
        } catch (Exception e) {
            return null;
        }
    }

    public static Convenio buscarConvenioByEmpresa(Empresa empresa) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            Convenio a = convenioDao.buscarByEmpresa(empresa);
            return a;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Convenio buscarConvenioByPessoa(Pessoa pessoa) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            Convenio a = convenioDao.buscarByPessoa(pessoa);
            return a;
        } catch (Exception e) {
            return null;
        }
    }
}