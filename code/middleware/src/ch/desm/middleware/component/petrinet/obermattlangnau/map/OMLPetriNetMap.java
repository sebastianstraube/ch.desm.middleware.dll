package ch.desm.middleware.component.petrinet.obermattlangnau.map;

import java.util.Map;

import ch.desm.middleware.component.ComponentMapBase;

public class OMLPetriNetMap extends ComponentMapBase{

	@Override
	public Map<String, String> getMap() {
		return map;
	}

    public String mapEndpointToBrokerMessage(String message) throws Exception {
        String mappedMessage = this.getKey(message);
        if(mappedMessage.isEmpty()) {
            throw new Exception("unable to map petri net message \"" + message + "\"");
        }
        return mappedMessage;
    }

    public String mapBrokerToEndpointMessage(String message) throws Exception {
        String mappedMessage = this.getValue(message);
        if(mappedMessage.isEmpty()) {
            throw new Exception("unable to map broker message \"" + message + "\"");
        }
        return mappedMessage;
    }

	@Override
	protected void initialize(){
		
		map.put("1.90.01","Lampen_1_F_VS_Stoer");
		map.put("1.90.03","Fstr_fg_F_Fahrt3");
		map.put("1.90.04","Lampen_1_F_rot");
		map.put("1.90.05","Lampen_1_F_gruenFB1");
		map.put("1.90.06","Fstr_ef_E_Fahrt1");
		map.put("1.90.07","Fstr_ef_E_Halt");
		map.put("1.90.09","Fstr_gf_G_Fahrt3");
		map.put("1.90.10","Fstr_gf_G_Halt");
		map.put("1.90.11","Fstr_gf_G_Fahrt3");
		map.put("1.90.12","EMM_D_Halt");
		map.put("1.90.13","EMM_D_Fahrt");
		map.put("1.90.14","EMM_C_Halt");
		map.put("1.90.15","EMM_C_Fahrt");
		map.put("1.90.16","Lampen_1_G_VS_Stoer");
		map.put("1.91.21","Lampen_1_FBV_vZB");
		map.put("1.91.22","BL_ZB_OM_RMM_nZB");
		map.put("1.90.31","Lampen_1_BL_vLN_rot");
		map.put("1.90.32","Lampen_1_BL_vLN_weiss");
		map.put("1.90.33","Lampen_1_BL_nLN_weiss");
		map.put("1.90.34","Lampen_1_BL_vLN_rot");
		map.put("1.90.35","Lampe_iso_egf_belegt");
		map.put("1.90.36","Lampe_Iso_1_belegt");
		map.put("1.90.37","Lampen_1_Fstr_ef");
		map.put("1.90.38","Lampe_Iso_ef_belegt");
		map.put("1.90.39","Lampe_Iso_cd_belegt");
		map.put("1.90.40","Lampen_1_Fstr_gf");
		map.put("1.90.41","Lampen_1_BL_nZB_rot");
		map.put("1.90.42","Lampen_1_BL_nZB_weiss");
		map.put("1.90.43","Lampen_1_BL_vZB_weiss");
		map.put("1.90.44","Lampen_1_BL_vZB_rot");
		map.put("1.91.01","BL_OM_LN_RMM_nLN");
		map.put("1.91.02","Lampen_1_FBV_vLN");
		map.put("1.91.03","Lampen_1_Stoer_Wecker");
		map.put("1.01.01","Lampen_1_WS1_Freig");
		map.put("1.01.02","Lampen_1_WS1_Ueberw");
		map.put("1.04.01","Stw_FSS_Sperrm_ein");
		map.put("1.04.02","Stw_FSS_Kuppelm_ein");
		map.put("9.99.04","ext_Vb_abl_vEMM");
		map.put("9.99.05","ext_Vb_abl_vLN");
		map.put("9.99.06","ext_Vb_abl_vZB");
		map.put("9.99.07","ext_Vb_abl_nEMM");
		map.put("9.99.08","ext_Vb_abl_nLN");
		map.put("9.99.09","ext_Vb_abl_nZB");
		map.put("3.01.01","Stw_WS_Sperrm_ein");
		map.put("3.04.01","Stw_FSS_Sperrm_ein");
		map.put("3.04.02","Stw_FSS_Kuppelm_ein");
		map.put("6.90.01","Kontakte_6_Anf_Durchf_nEMM");
		map.put("6.91.02","Kontakte_6_FBA_nZB");
		map.put("6.91.01","Kontakte_6_FBF_nZB");
		map.put("6.91.03","Kontakte_6_RM_nZB");
		map.put("6.91.04","Kontakte_6_FBA_nLN");
		map.put("6.91.05","Kontakte_6_FBA_nLN");
		map.put("6.91.06","Kontakte_6_FBF_nLN");
		map.put("6.91.07","Kontakte_6_BLU_EG");
		map.put("6.91.08","Kontakte_6_Gleist_EMM");
		map.put("6.91.09","Kontakte_6_Wecker_absch");
		map.put("6.91.10","Kontakte_6_NT_W1");
		map.put("6.91.11","Kontakte_6_Wbel_ein");
		map.put("6.91.12","Kontakte_6_Zeitsch_Umg");
		map.put("6.91.13","Kontakte_6_NAL");
		map.put("6.91.14","Kontakte_6_Gleist_ZB");
		map.put("6.91.15","Kontakte_6_ISU_Sign");
		map.put("7.91.01","Kontakte_7_WS1_plus0");
		map.put("7.91.02","Kontakte_7_WS1_minus1");
		map.put("7.91.04","Kontakte_7_WS1_minus0");
		map.put("8.91.02","Kontakte_8_FSS_1EGF");
		map.put("8.91.01","Kontakte_8_FSS_0EGF");
		map.put("8.91.03","Kontakte_8_FSS_10F");
		map.put("8.91.04","Kontakte_8_FSS_30F");
		map.put("8.91.05","Kontakte_8_FSS_45F");
		map.put("8.91.06","Kontakte_8_FSS_80F");
		map.put("8.91.07","Kontakte_8_FSS_90F");
		map.put("8.91.19","Kontakte_8_FSS_10EG");
		map.put("8.91.20","Kontakte_8_FSS_30EG");
		map.put("8.91.21","Kontakte_8_FSS_45EG");
		map.put("8.91.22","Kontakte_8_FSS_80EG");
		map.put("8.91.23","Kontakte_8_FSS_90EG");
		map.put("10.99.01","Iso_egf_belegt");
		map.put("10.99.03","Iso_1_belegt");
		map.put("10.99.04","Iso_1_belegt");
		map.put("10.99.07","Iso_ef_belegt");
		map.put("10.99.08","Iso_cd_belegt");
		map.put("10.99.09","Iso_1_frei");
		map.put("10.99.10","Iso_egf_frei");
		map.put("11.99.01","ext_Vb_FRA_vLN");
		map.put("11.99.03","ext_Vb_FBF_vLN");
		map.put("11.99.04","ILTIS_RM_LN_OM");
		map.put("11.99.05","ext_Vb_FBA_vZB");
		map.put("11.99.07","ILTIS_festgehalten_vZB");
		map.put("11.99.08","ILTIS_RM_ZB_OM");
		map.put("11.99.09","BL_OM_LN_vorgebl_v");
		map.put("11.99.10","BL_ZB_OM_vorgebl_v");
		map.put("9.99.01","ext_Vb_abl_vEMM");
		map.put("9.99.02","ext_Vb_abl_vLN");
		map.put("9.99.03","ext_Vb_abl_vZB");
		map.put("6.99.01","ext_Vb_abl_nEMM");
		map.put("6.99.02","ext_Vb_abl_nLN");
		map.put("6.99.03","ext_Vb_abl_nZB");
		map.put("12.99.01","ext_Vb_Ueberw_W1_plus");
		map.put("12.99.02","ext_Vb_Ueberw_W1_minus");
		map.put("90.99.01","ext_VbF_VS_Lampe_Warn_def");
		map.put("90.99.02","ext_VbF_VS_Lampe_Fahrt_def");
		map.put("90.99.04","Fstr_fg_Stoerung_F");
		map.put("90.99.05","ext_Vb_F_Lampe_rot_def");
		map.put("90.99.06","Fstr_fg_Stoerung_F");
		map.put("90.99.14","ext_Vb_G_VS_Lampe_Warn_def");
		map.put("90.99.15","ext_Vb_G_VS_Lampe_Fahrt_def");


    }

}
