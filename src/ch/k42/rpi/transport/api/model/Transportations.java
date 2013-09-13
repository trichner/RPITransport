package ch.k42.rpi.transport.api.model;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/13/13
 * Time: 1:05 AM
 * To change this template use File | Settings | File Templates.
 */
public enum Transportations {
        ICE_TGV_RJ("ice_tgv_rj",0),
        EC_IC("ec_ic",0),
        IR("ir",0),
        RE_D("re_d",0),
        SHIP("ship",0),
        S_SN_R("s_sn_r",0),
        BUS("bus",0),
        CABLEWAY("cableway",0),
        ARZ_EXT("arz_ext",0),
        TRAMWAY_UNDERGROUND("tramway_underground",9);
        private String type;
        private int categoryNumber;
        private Transportations(String str,int categoryNumber){
            this.type = str;
            this.categoryNumber = categoryNumber;
        }
        public String getType(){
            return type;
        }
        public static Transportations getByStringOrNull(String type){
            for(Transportations t : values()){
                if(t.type.equals(type)) return t;
            }
            return null;
        }
        public static Transportations getByCategoryNumberOrNull(int categoryNumber){
            for(Transportations t : values()){
                if(t.categoryNumber==(categoryNumber)) return t;
            }
            return null;
        }
}
