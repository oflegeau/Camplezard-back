package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Invoice;
import com.lezardrieux.back.back.modelDAO.DAO_InvoiceLine;
import com.lezardrieux.back.back.repoDAO.Repo_InvoiceLine;
import com.lezardrieux.back.front.model.InvoiceLine;
import com.lezardrieux.back.front.model.Reponse;
import com.lezardrieux.back.service.DBS_Invoice;
import com.lezardrieux.back.service.DBS_InvoiceLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.lezardrieux.back.BackApplication.trace;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class BtF_InvoiceLine implements DBS_InvoiceLine {

    @Autowired
    Repo_InvoiceLine repo_InvoiceLine;

    @Autowired
    DBS_Invoice dbs_Invoice;

    private double totalGross = 0;
    private double totalNet = 0;
    private double totalVta = 0;

    @Override
    public double getTotalGross() {
        return totalGross;
    }

    @Override
    public double getTotalNet() {
        return totalNet;
    }

    @Override
    public double getTotalVta() {
        return totalVta;
    }

    //------------------------------------------------------------------------------//

    private DAO_InvoiceLine getBack(InvoiceLine obj) {
        return new DAO_InvoiceLine(         obj.getDesign().substring(0, Math.min(30, obj.getDesign().length())),
                                            obj.getLibelle().substring(0, Math.min(255, obj.getLibelle().length())),
                                            obj.getQuantity(),
                                            obj.getPrice(),
                                            obj.getVat());
    }

    //------------------------------------------------------------------------------//
    
    @Override
    @Transactional
    public InvoiceLine get(Long id) {
        Optional<DAO_InvoiceLine> option = repo_InvoiceLine.findById(id);
        return option.map(DAO_InvoiceLine::getInvoiceLine).orElse(null);
    }

    @Override
    @Transactional
    public DAO_InvoiceLine getDAO(Long id) {
        Optional<DAO_InvoiceLine> option = repo_InvoiceLine.findById(id);
        return option.orElse(null);
    }

    //------------------------------------------------------------------------------//

    @Override
    public List<InvoiceLine> getList(List<DAO_InvoiceLine> list) {
        totalGross = 0;
        totalNet = 0;
        totalVta = 0;
        List<InvoiceLine> _List = new ArrayList<>();
        for (DAO_InvoiceLine _TObj : list) {
            _List.add(_TObj.getInvoiceLine());
            totalGross += _TObj.getQuantity() * _TObj.getPrice();
            if (_TObj.getVat() != 0) {
                totalNet += (_TObj.getQuantity() * _TObj.getPrice()) * (1 + _TObj.getVat() / 100.0);
                totalVta += (_TObj.getQuantity() * _TObj.getPrice()) * (_TObj.getVat() / 100.0);
            }
            else
                totalNet += _TObj.getQuantity() * _TObj.getPrice();
        }
        return _List;
    }

    //------------------------------------------------------------------------------//
    // POST
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public boolean create(Long idInvoice, InvoiceLine obj) {
        try {
            // ----------------------------------------- //
            // sauvegarde sans jointure pour création ID //
            // ----------------------------------------- //
            DAO_InvoiceLine dao_invoiceLine = repo_InvoiceLine.save(getBack(obj));
            // ----------------------------------------- //
            // gestion des jointures                     //
            // ----------------------------------------- //
            DAO_Invoice _parent = dbs_Invoice.getDAO(idInvoice);
            if (_parent == null) {
                LOGGER.error("DAO_ProjectInvoice null/ID = " + idInvoice);
                return false;
            }
            dao_invoiceLine = _parent.addLine(dao_invoiceLine);
            // ----------------------------------------- //
            repo_InvoiceLine.save(dao_invoiceLine);

            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    //------------------------------------------------------------------------------//
    // PUT
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse update(InvoiceLine obj) {
        try {
            // ----------------------------------------- //
            // récupération en base avant update         //
            // ----------------------------------------- //
            DAO_InvoiceLine dao_invoiceLine = getDAO(obj.getId());
            if (dao_invoiceLine == null) {
                LOGGER.error("DAO_InvoiceLine null/ID = " + obj.getId().toString());
                return new Reponse(HttpStatus.NOT_FOUND, "BtF_ProjectInvoiceLine/update/DAO_InvoiceLine null/ID = " + obj.getId().toString());
            }
            // ----------------------------------------- //
            // mise à jour                               //
            // ----------------------------------------- //
            dao_invoiceLine = dao_invoiceLine
                    .setDesign(obj.getDesign().substring(0, Math.min(30, obj.getDesign().length())))
                    .setLibelle(obj.getLibelle().substring(0, Math.min(255, obj.getLibelle().length())))
                    .setQuantity(obj.getQuantity())
                    .setPrice(obj.getPrice())
                    .setVat(obj.getVat());
            // ----------------------------------------- //
            repo_InvoiceLine.save(dao_invoiceLine);

            if (trace) LOGGER.warn(dao_invoiceLine.toString());
            return new Reponse(HttpStatus.ACCEPTED, "", dao_invoiceLine.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_ProjectInvoiceLine/update " + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------//
    // DELETE
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse delete(Long id) {
        try {
            if (id.equals(0L)) {
                LOGGER.error("id null/ID = " + id);
                return new Reponse(HttpStatus.NOT_FOUND, "BtF_ProjectInvoiceLine/delete/id null/ID = " + id.toString());
            }
            // ----------------------------------------- //
            repo_InvoiceLine.deleteById(id);

            if (trace) LOGGER.warn(id.toString());
            return new Reponse(HttpStatus.OK, "", id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_ProjectInvoiceLine/delete " + e.getMessage());
        }
    }

}
