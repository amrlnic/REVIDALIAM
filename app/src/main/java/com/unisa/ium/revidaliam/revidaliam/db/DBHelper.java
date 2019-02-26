package com.unisa.ium.revidaliam.revidaliam.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_revidaliam.db";
    private Context mContext;
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        mContext = context;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts where id = " + id + "", null);
        return res;
    }

    public boolean updateContact(UtenteBean ub, String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", ub.getUsername());
        contentValues.put("nome", ub.getNome());
        contentValues.put("cognome", ub.getCognome());
        contentValues.put("email", ub.getEmail());
        contentValues.put("indirizzo", ub.getIndirizzo());
        db.update("Utente", contentValues, "username = ?", new String[]{key});
        return true;
    }

    public boolean updateContact(VolontarioBean vb, String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", vb.getUsername());
        contentValues.put("nome", vb.getNome());
        contentValues.put("cognome", vb.getCognome());
        contentValues.put("email", vb.getEmail());
        contentValues.put("indirizzo", vb.getIndirizzo());
        db.update("Volontario", contentValues, "username = ?", new String[]{key});
        return true;
    }

    public boolean updateContact(SupermercatoBean sb, String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", sb.getUsername());
        contentValues.put("nome", sb.getNome());
        contentValues.put("email", sb.getEmail());
        contentValues.put("indirizzo", sb.getIndirizzo());
        db.update("Supermercato", contentValues, "username = ?", new String[]{key});
        return true;
    }

    public boolean updateContact(ProdottoBean pb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idProdotto", pb.getIdProdotto());
        contentValues.put("name", pb.getNome());
        contentValues.put("prezzo", pb.getPrezzo());
        contentValues.put("descrizione", pb.getDescrizione());
        db.update("Prodotto", contentValues, "idProdotto = ?", new String[]{Integer.toString(pb.getIdProdotto())});
        return true;
    }

    public boolean updateContact(RichiestaBean rb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idRichiesta", rb.getIdRichiesta());
        contentValues.put("idUtente", rb.getIdUtente());
        contentValues.put("idOrdine", rb.getIdOrdine());
        contentValues.put("stato", rb.isStato());

        db.update("Richiesta", contentValues, "idRichiesta = ? ", new String[]{Integer.toString(rb.getIdRichiesta())});
        return true;
    }

    public boolean updateContact(OrdineBean ob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idUtente", ob.getIdUtente());
        contentValues.put("idOrdine", ob.getIdOrdine());

        db.update("Ordine", contentValues, "idOrdine = ? ", new String[]{Integer.toString(ob.getIdOrdine())});
        return true;
    }

    public boolean insertContact(OrdineBean ob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idUtente", ob.getIdUtente());

        db.insert("Ordine", null, contentValues);
        return true;
    }

    public boolean insertContact(UtenteBean ub) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", ub.getUsername());
        contentValues.put("nome", ub.getNome());
        contentValues.put("cognome", ub.getCognome());
        contentValues.put("email", ub.getEmail());
        contentValues.put("password", ub.getPassword());
        contentValues.put("indirizzo", ub.getIndirizzo());
        db.insert("Utente", null, contentValues);
        return true;
    }

    public boolean insertContact(VolontarioBean vb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", vb.getUsername());
        contentValues.put("nome", vb.getNome());
        contentValues.put("cognome", vb.getCognome());
        contentValues.put("email", vb.getEmail());
        contentValues.put("password", vb.getPassword());
        contentValues.put("indirizzo", vb.getIndirizzo());
        db.insert("Volontario", null, contentValues);
        return true;
    }

    public boolean insertContact(SupermercatoBean sb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", sb.getUsername());
        contentValues.put("nome", sb.getNome());
        contentValues.put("email", sb.getEmail());
        contentValues.put("password", sb.getPassword());
        contentValues.put("indirizzo", sb.getIndirizzo());
        db.insert("Supermercato", null, contentValues);
        return true;
    }

    public boolean insertContact(RichiestaBean rb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idRichiesta", rb.getIdRichiesta());
        contentValues.put("idUtente", rb.getIdUtente());
        contentValues.put("idOrdine", rb.getIdOrdine());
        contentValues.put("stato", rb.isStato());

        db.insert("Richiesta", null, contentValues);
        return true;
    }

    public boolean insertContact(ProdottoBean pb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", pb.getNome());
        contentValues.put("prezzo", pb.getPrezzo());
        contentValues.put("descrizione", pb.getDescrizione());

        db.insert("Prodotto", null, contentValues);
        return true;
    }

    public boolean insertContact(ProdottoSupermercatoBean pb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idProdotto", pb.getIdProdotto());
        contentValues.put("username", pb.getUsernameSupermercato());

        db.insert("ProdottiSupermercato", null, contentValues);
        return true;
    }

    public boolean insertContact(RigaOrdineBean rob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idProdotto", rob.getIdProdotto());
        contentValues.put("idRigaOrdine", rob.getIdRigaOrdine());
        contentValues.put("idOrdine", rob.getIdOrdine());
        contentValues.put("idSupermercato", rob.getIdSupermercato());
        contentValues.put("quantita", rob.getQuantita());
        db.insert("RigaOrdine", null, contentValues);
        return true;
    }


    public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public int deleteSupermercato(int username){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("ProdottiSupermercato", "idProdotto = ?", new String[]{Integer.toString(username)});
    }
    public UtenteBean retrieveUtenteContacts(String idUtente, String password) {
        ArrayList<String> array_list = new ArrayList<String>();
        UtenteBean u = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Utente where username = " + "\"" + idUtente + "\"" + " AND password = " + "\"" + password + "\"" + ";";
        System.out.println(query);
        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        if (res.isAfterLast() == false) {
            u = new UtenteBean();
            u.setUsername(res.getString(res.getColumnIndex("username")));
            u.setNome(res.getString(res.getColumnIndex("nome")));
            u.setCognome(res.getString(res.getColumnIndex("cognome")));
            u.setEmail(res.getString(res.getColumnIndex("email")));
            u.setIndirizzo(res.getString(res.getColumnIndex("indirizzo")));
            u.setPassword(res.getString(res.getColumnIndex("password")));

        }
        return u;
    }

    public UtenteBean retrieveUtenteContacts(String idUtente) {
        ArrayList<String> array_list = new ArrayList<String>();
        UtenteBean u = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Utente where username = " + "\"" + idUtente + "\"" + ";";
        System.out.println(query);
        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        if (res.isAfterLast() == false) {
            u = new UtenteBean();
            u.setUsername(res.getString(res.getColumnIndex("username")));
            u.setNome(res.getString(res.getColumnIndex("nome")));
            u.setCognome(res.getString(res.getColumnIndex("cognome")));
            u.setEmail(res.getString(res.getColumnIndex("email")));
            u.setIndirizzo(res.getString(res.getColumnIndex("indirizzo")));
            u.setPassword(res.getString(res.getColumnIndex("password")));

        }
        return u;
    }

    public SupermercatoBean retrieveSupermercatoContacts(String idUtente, String password) {
        ArrayList<String> array_list = new ArrayList<String>();
        SupermercatoBean u = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Supermercato where username = " + "\"" + idUtente + "\"" + " AND password = " + "\"" + password + "\"" + ";";
        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        if (res.isAfterLast() == false) {
            u = new SupermercatoBean();
            u.setUsername(res.getString(res.getColumnIndex("username")));
            u.setNome(res.getString(res.getColumnIndex("nome")));
            u.setEmail(res.getString(res.getColumnIndex("email")));
            u.setIndirizzo(res.getString(res.getColumnIndex("indirizzo")));
            u.setPassword(res.getString(res.getColumnIndex("password")));

        }
        return u;
    }

    public SupermercatoBean retrieveSupermercatoContacts(String idUtente) {
        ArrayList<String> array_list = new ArrayList<String>();
        SupermercatoBean u = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Supermercato where username = " + "\"" + idUtente + "\"" + ";";
        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        if (res.isAfterLast() == false) {
            u = new SupermercatoBean();
            u.setUsername(res.getString(res.getColumnIndex("username")));
            u.setNome(res.getString(res.getColumnIndex("nome")));
            u.setEmail(res.getString(res.getColumnIndex("email")));
            u.setIndirizzo(res.getString(res.getColumnIndex("indirizzo")));
            u.setPassword(res.getString(res.getColumnIndex("password")));
        }
        return u;
    }

    public VolontarioBean retrieveVolontarioContacts(String idUtente, String password) {
        ArrayList<String> array_list = new ArrayList<String>();
        VolontarioBean u = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Volontario where username = " + "\"" + idUtente + "\"" + " AND password = " + "\"" + password + "\"" + ";";
        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        if (res.isAfterLast() == false) {
            u = new VolontarioBean();
            u.setUsername(res.getString(res.getColumnIndex("username")));
            u.setNome(res.getString(res.getColumnIndex("nome")));
            u.setCognome(res.getString(res.getColumnIndex("cognome")));
            u.setEmail(res.getString(res.getColumnIndex("email")));
            u.setIndirizzo(res.getString(res.getColumnIndex("indirizzo")));
            u.setPassword(res.getString(res.getColumnIndex("password")));

        }
        return u;
    }

    public VolontarioBean retrieveVolontarioContacts(String idUtente) {
        ArrayList<String> array_list = new ArrayList<String>();
        VolontarioBean u = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Volontario where username = " + "\"" + idUtente + "\"" + ";";
        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        if (res.isAfterLast() == false) {
            u = new VolontarioBean();
            u.setUsername(res.getString(res.getColumnIndex("username")));
            u.setNome(res.getString(res.getColumnIndex("nome")));
            u.setCognome(res.getString(res.getColumnIndex("cognome")));
            u.setEmail(res.getString(res.getColumnIndex("email")));
            u.setIndirizzo(res.getString(res.getColumnIndex("indirizzo")));
            u.setPassword(res.getString(res.getColumnIndex("password")));
        }
        return u;
    }

    public ProdottoBean retrieveProdottoContacts(String idProdotto) {
        ArrayList<String> array_list = new ArrayList<String>();
        ProdottoBean p = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Prodotto where idProdotto = " + "\"" + idProdotto + "\"" + ";";
        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        if (res.isAfterLast() == false) {
            p = new ProdottoBean();
            p.setNome(res.getString(res.getColumnIndex("nome")));
            p.setIdProdotto(res.getInt(res.getColumnIndex("idProdotto")));
            p.setPrezzo(res.getFloat(res.getColumnIndex("prezzo")));
            p.setDescrizione(res.getString(res.getColumnIndex("descrizione")));
        }
        return p;
    }

    public SupermercatoBean[] retrieveAllSupermercatoContacts() {
        SupermercatoBean[] marketList = new SupermercatoBean[100];

        SupermercatoBean u = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Supermercato;";
        Cursor res = db.rawQuery(query, null);
        int i = 0;
        while (res.moveToNext() != false) {
            u = new SupermercatoBean();
            u.setUsername(res.getString(res.getColumnIndex("username")));
            u.setNome(res.getString(res.getColumnIndex("nome")));
            u.setEmail(res.getString(res.getColumnIndex("email")));
            u.setIndirizzo(res.getString(res.getColumnIndex("indirizzo")));
            u.setPassword(res.getString(res.getColumnIndex("password")));

            marketList[i] = u;
            i++;

        }
        SupermercatoBean[] listaDefinitiva = new SupermercatoBean[i];

        i = 0;

        while (marketList[i] != null) {
            listaDefinitiva[i] = marketList[i];
            i++;
        }
        return listaDefinitiva;
    }

    public ProdottoBean[] retrieveAllProdottoContacts() {
        ProdottoBean[] productList = new ProdottoBean[100];

        ProdottoBean pb = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Prodotto;";
        Cursor res = db.rawQuery(query, null);
        int i = 0;
        while (res.moveToNext() != false) {
            pb = new ProdottoBean();
            pb.setNome(res.getString(res.getColumnIndex("nome")));
            pb.setIdProdotto(res.getInt(res.getColumnIndex("idProdotto")));
            pb.setPrezzo(res.getFloat(res.getColumnIndex("prezzo")));
            pb.setDescrizione(res.getString(res.getColumnIndex("descrizione")));

            productList[i] = pb;
            i++;

        }
        ProdottoBean[] listaDefinitiva = new ProdottoBean[i];

        i = 0;

        while (productList[i] != null) {
            listaDefinitiva[i] = productList[i];
            i++;
        }
        return listaDefinitiva;
    }

    public ProdottoBean[] retrieveAllProdottoFromMarketContacts(String idMarket) {
        ProdottoBean[] productList = new ProdottoBean[100];

        ProdottoBean pb = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Prodotto p, ProdottiSupermercato ps where ps.username =\"" + idMarket + "\" and p.idProdotto = ps.idProdotto;";
        Cursor res = db.rawQuery(query, null);
        int i = 0;
        while (res.moveToNext() != false) {
            pb = new ProdottoBean();
            pb.setNome(res.getString(res.getColumnIndex("nome")));
            pb.setIdProdotto(res.getInt(res.getColumnIndex("idProdotto")));
            pb.setPrezzo(res.getFloat(res.getColumnIndex("prezzo")));
            pb.setDescrizione(res.getString(res.getColumnIndex("descrizione")));

            productList[i] = pb;
            i++;

        }
        ProdottoBean[] listaDefinitiva = new ProdottoBean[i];

        i = 0;

        while (productList[i] != null) {
            listaDefinitiva[i] = productList[i];
            i++;
        }
        return listaDefinitiva;
    }

    public OrdineBean[] retrieveAllOrdineByUserContacts(String username) {
        OrdineBean[] orderList = new OrdineBean[100];

        OrdineBean ob = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Ordine o, Utente u where u.username =\"" + username + "\" and u.username = o.idUtente;";
        Cursor res = db.rawQuery(query, null);
        int i = 0;
        while (res.moveToNext() != false) {
            ob = new OrdineBean();
            ob.setIdOrdine(res.getInt(res.getColumnIndex("idOrdine")));
            ob.setIdUtente(res.getString(res.getColumnIndex("idUtente")));

            orderList[i] = ob;
            i++;

        }
        OrdineBean[] listaDefinitiva = new OrdineBean[i];

        i = 0;

        while (orderList[i] != null) {
            listaDefinitiva[i] = orderList[i];
            i++;
        }
        return listaDefinitiva;
    }


    public int retrieveMaxProdottoContacts() {

        ProdottoBean p = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select MAX(idProdotto) AS massimo from Prodotto;";

        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();

        if (res.isAfterLast() == false) {
            p = new ProdottoBean();
            p.setIdProdotto(res.getInt(res.getColumnIndex("massimo")));
        }
        return p.getIdProdotto();
    }

    public int retrieveMaxOrdineContacts() {

        OrdineBean o = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select MAX(idOrdine) AS massimo from Ordine;";

        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();

        if (res.isAfterLast() == false) {
            o = new OrdineBean();
            o.setIdOrdine(res.getInt(res.getColumnIndex("massimo")));
        }
        return o.getIdOrdine();
    }

    public RigaOrdineBean[] retrieveAllRigaOrdineByOrdineContacts(int idOrdine) {

        RigaOrdineBean[] rigaList = new RigaOrdineBean[100];

        RigaOrdineBean rob = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from RigaOrdine ro, Ordine o where o.idOrdine =\"" + idOrdine + "\" and ro.idOrdine = o.idOrdine;";
        Cursor res = db.rawQuery(query, null);
        int i = 0;
        while (res.moveToNext() != false) {
            rob = new RigaOrdineBean();
            rob.setIdOrdine(res.getInt(res.getColumnIndex("idOrdine")));
            rob.setIdRigaOrdine(res.getInt(res.getColumnIndex("idRigaOrdine")));
            rob.setIdProdotto(res.getInt(res.getColumnIndex("idProdotto")));
            rob.setIdSupermercato(res.getString(res.getColumnIndex("idSupermercato")));
            rob.setQuantita(res.getInt(res.getColumnIndex("quantita")));

            rigaList[i] = rob;
            i++;

        }
        RigaOrdineBean[] listaDefinitiva = new RigaOrdineBean[i];

        i = 0;

        while (rigaList[i] != null) {
            listaDefinitiva[i] = rigaList[i];
            i++;
        }
        return listaDefinitiva;
    }

    public ArrayList<RichiestaBean> retrieveRichiestaByCityContacts(String city) {
        ArrayList<RichiestaBean> array_list = new ArrayList<>();
        RichiestaBean r = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryrichiesta = "Select * from Richiesta";
        Cursor res = db.rawQuery(queryrichiesta, null);
        Log.d("StepDBHelper","arrivato");
        res.moveToFirst();
        Log.d("DBHelperSize:",res.getColumnCount()+"");
        if(res.isAfterLast()==false) {
            do {
                r = new RichiestaBean();
                r.setIdOrdine(res.getInt(res.getColumnIndex("idOrdine")));
                Log.d("ListaRichieste", r.getIdOrdine() + "");
                r.setIdRichiesta(res.getInt(res.getColumnIndex("idRichiesta")));
                r.setIdUtente(res.getString(res.getColumnIndex("idUtente")));
                array_list.add(r);
            } while (res.moveToNext() != false);
        }
        return array_list;
    }

    public int retrieveCountRigheOrdineContacts(int idOrdine) {

        ProdottoBean p = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select Count(idRigaOrdine) AS numeroProdotti from RigaOrdine where idOrdine="+idOrdine+";";

        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();

        if (res.isAfterLast() == false) {
            return res.getInt(res.getColumnIndex("numeroProdotti"));
        }
        return 0;
    }

    public String retrieveSupermercatoByOrdine(int idOrdine) {

        RichiestaBean r = null;
        String idSupermercato="";
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryrichiesta = "Select * from RigaOrdine where idOrdine="+idOrdine+";";
        Cursor res = db.rawQuery(queryrichiesta, null);
        res.moveToFirst();
        if (res.isAfterLast() == false) {
            idSupermercato=res.getString(res.getColumnIndex("idSupermercato"));
        }
        return idSupermercato;
    }

    public SupermercatoBean retrieveSupermercatoBeanByOrdine(String idSupermercato) {

        RichiestaBean r = null;
        SupermercatoBean sb=new SupermercatoBean();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryrichiesta = "Select * from Supermercato where username=\""+idSupermercato+"\";";
        Cursor res = db.rawQuery(queryrichiesta, null);
        res.moveToFirst();
        if (res.isAfterLast() == false) {
            sb.setUsername(res.getString(res.getColumnIndex("username")));
            sb.setNome(res.getString(res.getColumnIndex("nome")));
            sb.setIndirizzo(res.getString(res.getColumnIndex("indirizzo")));
            sb.setEmail(res.getString(res.getColumnIndex("email")));
        }
        return sb;
    }

    public OrdineBean retrieveOrdineContact(int idOrdine) {

        RichiestaBean r = null;
        OrdineBean ob=null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryrichiesta = "Select * from Ordine where idOrdine="+idOrdine+";";
        Cursor res = db.rawQuery(queryrichiesta, null);
        res.moveToFirst();
        if (res.isAfterLast() == false) {
            ob=new OrdineBean();
            ob.setIdUtente(res.getString(res.getColumnIndex("idUtente")));
            ob.setIdOrdine(res.getInt(res.getColumnIndex("idOrdine")));
        }
        return ob;
    }

    public RichiestaBean retrieveRichiestaContact(int idRichiesta) {

        RichiestaBean r = null;

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryrichiesta = "Select * from Richiesta where idRichiesta="+idRichiesta+";";
        Cursor res = db.rawQuery(queryrichiesta, null);
        res.moveToFirst();
        if (res.isAfterLast() == false) {
            r=new RichiestaBean();
            r.setIdRichiesta(res.getInt(res.getColumnIndex("idRichiesta")));
            r.setIdUtente(res.getString(res.getColumnIndex("idUtente")));
            r.setIdOrdine(res.getInt(res.getColumnIndex("idOrdine")));
        }
        return r;
    }

    public ArrayList<RichiestaBean> retrieveRichiestaByUserContacts(String username) {
        ArrayList<RichiestaBean> array_list = new ArrayList<>();
        RichiestaBean r = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryrichiesta = "Select * from Richiesta where idUtente=\""+username+"\";";
        Cursor res = db.rawQuery(queryrichiesta, null);
        Log.d("StepDBHelper","arrivato");
        res.moveToFirst();
        Log.d("DBHelperSize:",res.getColumnCount()+"");
        if(res.isAfterLast()==false) {
            do {
                r = new RichiestaBean();
                r.setIdOrdine(res.getInt(res.getColumnIndex("idOrdine")));
                Log.d("ListaRichieste", r.getIdOrdine() + "");
                r.setIdRichiesta(res.getInt(res.getColumnIndex("idRichiesta")));
                r.setIdUtente(res.getString(res.getColumnIndex("idUtente")));
                array_list.add(r);
            } while (res.moveToNext() != false);
        }
        return array_list;
    }

}
