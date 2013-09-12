package ch.k42.rpi.transport.api.model;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/13/13
 * Time: 1:05 AM
 * To change this template use File | Settings | File Templates.
 */
public enum Transportations {
        ICE_TGV_RJ("ice_tgv_rj"),
        EC_IC("ec_ic"),
        IR("ir"),
        RE_D("re_d"),
        SHIP("ship"),
        S_SN_R("s_sn_r"),
        BUS("bus"),
        CABLEWAY("cableway"),
        ARZ_EXT("arz_ext"),
        TRAMWAY_UNDERGROUND("tramway_underground");
        private String type;
        private Transportations(String str){
            this.type = str;
        }
        public String getType(){
            return type;
        }
}
