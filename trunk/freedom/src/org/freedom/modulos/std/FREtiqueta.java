/**
 * @version 19/12/2003 <BR>
 * @author Setpoint Inform�tica Ltda./Fernando Oliveira da Silva <BR>
 *
 * Projeto: Freedom <BR>
 *   
 * Pacote: org.freedom.modulos.std <BR>
 * Classe: @(#)FRBoleto.java <BR>
 * 
 * Este programa � licenciado de acordo com a LPG-PC (Licen�a P�blica Geral para Programas de Computador), <BR>
 * vers�o 2.1.0 ou qualquer vers�o posterior. <BR>
 * A LPG-PC deve acompanhar todas PUBLICA��ES, DISTRIBUI��ES e REPRODU��ES deste Programa. <BR>
 * Caso uma c�pia da LPG-PC n�o esteja dispon�vel junto com este Programa, voc� pode contatar <BR>
 * o LICENCIADOR ou ent�o pegar uma c�pia em: <BR>
 * Licen�a: http://www.lpg.adv.br/licencas/lpgpc.rtf <BR>
 * Para poder USAR, PUBLICAR, DISTRIBUIR, REPRODUZIR ou ALTERAR este Programa � preciso estar <BR>
 * de acordo com os termos da LPG-PC <BR> <BR>
 *
 * Coment�rios sobre a classe...
 * 
 */

package org.freedom.modulos.std;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import org.freedom.componentes.JLabelPad;
import org.freedom.componentes.GuardaCampo;
import org.freedom.componentes.ImprimeOS;
import org.freedom.componentes.JTextAreaPad;
import org.freedom.componentes.JTextFieldFK;
import org.freedom.componentes.JTextFieldPad;
import org.freedom.componentes.ListaCampos;
import org.freedom.funcoes.Funcoes;
import org.freedom.telas.Aplicativo;
import org.freedom.telas.FRelatorio;

public class FREtiqueta extends FRelatorio {
  private JTextFieldPad txtCodModEtiq = new JTextFieldPad(JTextFieldPad.TP_INTEGER,8,0);
  private JTextFieldFK txtDescModEtiq = new JTextFieldFK(JTextFieldPad.TP_STRING,50,0);
  private JTextFieldPad txtCodSetor = new JTextFieldPad(JTextFieldPad.TP_INTEGER,8,0); 
  private JTextFieldFK txtDescSetor = new JTextFieldFK(JTextFieldPad.TP_STRING,50,0);
  private JTextFieldPad txtCodTipo = new JTextFieldPad(JTextFieldPad.TP_INTEGER,8,0); 
  private JTextFieldFK txtDescTipo = new JTextFieldFK(JTextFieldPad.TP_STRING,50,0);
  private ListaCampos lcModEtiq = new ListaCampos(this);
  private ListaCampos lcSetor = new ListaCampos(this);
  private ListaCampos lcTipo = new ListaCampos(this);
  private JTextAreaPad txaEtiqueta = new JTextAreaPad(500);
  private JTextFieldPad txtNColModEtiq = new JTextFieldPad(JTextFieldPad.TP_INTEGER,5,0);
  private ObjetoEtiquetaCli objEtiqCli = new ObjetoEtiquetaCli();
    
  public FREtiqueta() {
     setTitulo("Impress�o de etiquetas");
     setAtribos(80,80,480,240);
               
     lcModEtiq.add(new GuardaCampo( txtCodModEtiq, "CodModEtiq", "C�d.mod.", ListaCampos.DB_PK,true));
     lcModEtiq.add(new GuardaCampo( txtDescModEtiq, "DescModEtiq", "Descri��o do modelo de etiqueta", ListaCampos.DB_SI,false));
     lcModEtiq.add(new GuardaCampo( txaEtiqueta,"TxaModEtiq","Corpo",ListaCampos.DB_SI,false));
     lcModEtiq.add(new GuardaCampo( txtNColModEtiq,"NColModEtiq","Colunas",ListaCampos.DB_SI,false));
     lcModEtiq.setReadOnly(true);
     lcModEtiq.montaSql(false, "MODETIQUETA", "SG");
     txtCodModEtiq.setTabelaExterna(lcModEtiq);
     txtCodModEtiq.setFK(true);
     txtCodModEtiq.setNomeCampo("CodModEtiq");
    
     lcSetor.add(new GuardaCampo( txtCodSetor, "CodSetor", "C�d.setor", ListaCampos.DB_PK,false));
     lcSetor.add(new GuardaCampo( txtDescSetor, "DescSetor", "Descri��o do setor", ListaCampos.DB_SI,false));
     lcSetor.setReadOnly(true);
     lcSetor.montaSql(false, "SETOR", "VD");
     txtCodSetor.setTabelaExterna(lcSetor);
     txtCodSetor.setFK(true);
     txtCodSetor.setNomeCampo("CodSetor");
     
     lcTipo.add(new GuardaCampo( txtCodTipo, "CodTipoCli", "C�d.tp.cli.", ListaCampos.DB_PK,false));
     lcTipo.add(new GuardaCampo( txtDescTipo,"DescTipoCli", "Descri��o do tipo de cliente",ListaCampos.DB_SI,false));
     lcTipo.setReadOnly(true);
     lcTipo.montaSql(false, "TIPOCLI", "VD");
     txtCodTipo.setTabelaExterna(lcTipo);
     txtCodTipo.setFK(true);
     txtCodTipo.setNomeCampo("CodTipoCli");
              
     adic(new JLabelPad("C�d.setor"),7,5,280,20);
     adic(txtCodSetor,7,25,80,20);
     adic(new JLabelPad("Descri��o do setor"),90,5,280,20);
     adic(txtDescSetor,90,25,200,20);
     adic(new JLabelPad("C�d.tp.cli."),7,45,280,20);
     adic(txtCodTipo,7,65,80,20);
     adic(new JLabelPad("Descri��o do tipo de cliente"),90,45,280,20);
     adic(txtDescTipo,90,65,200,20);
     adic(new JLabelPad("C�d.mod."),7,85,280,20);
     adic(txtCodModEtiq,7,105,80,20);
     adic(new JLabelPad("Descri��o do modelo"),90,85,280,20);
     adic(txtDescModEtiq,90,105,200,20);
  }   
  
  public void setConexao(Connection cn) {
    super.setConexao(cn);
    lcModEtiq.setConexao(cn);
    lcSetor.setConexao(cn);
    lcTipo.setConexao(cn);
  }

  public void imprimir(boolean bVisualizar) {
    String sTxa = txaEtiqueta.getVlrString();
      
    PreparedStatement ps = null;
	ResultSet rs = null;
	int iNColModEtiq = 0;
		
	String sSQL = "";
	String sWhere = "";
	String sSep = "";
	String sLinha = "";
	
	ImprimeOS imp = null;
    try {
  	    if (txtCodModEtiq.getVlrString().equals("")) {
  	       Funcoes.mensagemInforma(this,"C�digo do modelo em branco!");
  	       return;
  	    }
  	    imp = new ImprimeOS("",con);
  	    imp.verifLinPag();
  	    imp.setTitulo("Etiquetas");

  	    if (sTxa!=null) {

  	  	   objEtiqCli.setTexto(sTxa);
  	       sSQL = montaQuery("VDCLIENTE");
  	          
  	    	try {
  	    		ps = con.prepareStatement(sSQL);
  	    		rs = ps.executeQuery();
  	    		Vector vCol = new Vector();
  	    		Vector vVal = new Vector();

  	    		while ( rs.next() ) { 
  	    			vVal = aplicCampos(rs); 
  	    			if (vVal != null){ 
                		vCol.addElement(vVal);
                	}

  	    		}

  	    		impCol(imp,vCol);
  				
  	    		rs.close();
  	    		ps.close();
  	    		if (!con.getAutoCommit()) 
  	    			con.commit();
  	    		
  	    	}
  	    	catch ( SQLException err ) {
  	    		Funcoes.mensagemErro(this,"Erro ao consultar informa��es!"+err.getMessage());
  	    		err.printStackTrace();      
  	    	}
  	    	imp.eject();
  	    	imp.fechaGravacao();
  	    	if (bVisualizar) {
  	    		imp.preview(this);
  	    	}
  	    	else {
  	    		imp.print();
  	    	}
  	    }
  	}
  	finally {
  		ps = null;
  		rs = null;
  		sSQL = null;
  		sWhere = null;
  		sSep = null;
  		sLinha = null;
  	}
	
  }
  private String montaQuery(String sTabela){
      String sCampos = "";
      String sSQL = "";
      Vector vCamposAdic = objEtiqCli.getCamposAdic();
      String sWhere = "WHERE CODEMP="+Aplicativo.iCodEmp+" AND CODFILIAL="+ListaCampos.getMasterFilial(sTabela);
      
      try {
          if (!txtCodSetor.getVlrString().equals("")) {
  	   		sWhere += " AND CODSETOR="+txtCodSetor.getVlrInteger().intValue();
  	   		sWhere += " AND CODEMPSR="+Aplicativo.iCodEmp;
  	   		sWhere += " AND CODFILIALSR="+lcSetor.getCodFilial();
        }
  	  if (!txtCodTipo.getVlrString().equals("")) {
  	    	sWhere += " AND CODTIPOCLI="+txtCodTipo.getVlrInteger().intValue();
  	    	sWhere += " AND CODEMPTC="+Aplicativo.iCodEmp;
  	    	sWhere += " AND CODFILIALTC="+lcTipo.getCodFilial();
  	  }
         
      for(int i=0;vCamposAdic.size()>i;i++){
          sCampos = sCampos + vCamposAdic.elementAt(i).toString()+",";    
      }
       
      sSQL = "SELECT "+sCampos.substring(0,sCampos.length()-1)+" FROM "+sTabela+" "+sWhere;
      }
      catch(Exception e){
          e.printStackTrace();
      }
      return sSQL;      
  }
  private void impCol(ImprimeOS imp, Vector vVals) {
 	int iCols = txtNColModEtiq.getVlrInteger().intValue();
    try {
        for(int i=0;vVals.size()>i;i++){  
            for(int i2=0;((Vector)vVals.elementAt(i)).size()>i2;i2++) {
                imp.say(imp.pRow()+1,0,((Vector)vVals.elementAt(i)).elementAt(i2).toString());
                System.out.println(((Vector)vVals.elementAt(i)).elementAt(i2).toString());
            }
        }
  	}
    catch(Exception e){
        e.printStackTrace();
    }
  	finally {
  	    vVals = null;
  	}  	
  }
  
private Vector aplicCampos(ResultSet rs) {
  	String sCampo = "";
  	String sRetorno = txaEtiqueta.getVlrString();
  	sRetorno = sRetorno.replaceAll("\\"+"\n","[Q]");
  	Vector vRet = null;
  	try {
// Estes '\\' que  aparecem por ai..s�o para anular caracteres especiais de "express�o regular".

  	    Vector vTamsAdic = objEtiqCli.getTamsAdic();
  	    Vector vMascAdic = objEtiqCli.getMascarasAdic();
  	    Vector vValAdic = objEtiqCli.getValoresAdic();
  	    Vector vCamposAdic = objEtiqCli.getCamposAdic();
  	    if (sRetorno != null) { 
		   	try {			    	    
		   	    for(int i=0;vCamposAdic.size()>i;i++) {
		   	        String sTmp = vCamposAdic.elementAt(i).toString();
		   	        String sValAdic = vValAdic.elementAt(i).toString();
//		   	        String sFragmento = sRetorno.substring(sRetorno.indexOf("["+sValAdic),sRetorno.indexOf("]")+1);
		   	        String sFragmento = sRetorno.substring(sRetorno.indexOf("["+sValAdic));
		   	        sFragmento = sFragmento.substring(0,("\\"+sFragmento).indexOf("]"));
		   	        sCampo = (rs.getString(sTmp)!=null?rs.getString(sTmp).trim():"");

		   	        if(vMascAdic.elementAt(i)!=null)
		    	        sCampo = Funcoes.setMascara(sCampo, vMascAdic.elementAt(i).toString());
		    	    
		   	        int iTmp = Funcoes.contaChar(sFragmento,'-'); 
		    	    
		   	        if (sCampo.length()>=iTmp)
		    	        sCampo = sCampo.substring(0,iTmp);
		    	    else 
		    	        sCampo = sCampo+Funcoes.replicate(" ",iTmp-sCampo.length());

		    	    sRetorno = sRetorno.replaceAll("\\"+sFragmento,sCampo);	
		    	   
		    	}
//		   	    sRetorno = sRetorno.replaceAll("[Q]","\n");
		   	}						
			catch (SQLException e) {
				Funcoes.mensagemErro(this,"Erro na troca de dados!\n"+e.getMessage());
			}
  		}
		vRet = Funcoes.stringToVector(sRetorno,"[Q]");
       	}
  	finally {
  		sCampo = null;
  	}
  	return vRet;
  }
public static String Tiracp(String sVal, int Tam) {// teste
	 char[] cVal = sVal.toCharArray();// teste
	    String sRetorno = sVal;// teste
	    for (int i=sVal.length()-1;i>=0;i--) {// teste
	      if (i <= Tam) {// teste
	      	sRetorno = sVal.substring(0,i+1); // teste
	        break;// teste
	      }// teste
	    }// teste
	    return  sRetorno;// teste
	  }
}
