import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pe.com.cablered.mistia.ia.clustering.Point;
import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.util.Util;

public class Test1 {
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		
		  String campos  = "numero_contrato, "
		  		+ "fecha_inicio, "
		  		+ "fecha_fin,"
		  		+ "tarifa, "
		  		+ "codigo_poste, "
		  		+ "direccion,"
		  		+ "codigo_distrito, "
		  		+ "fecha_creacion,"
		  		+ "usuario_creacion, "
		  		+ "estacion_creacion, "
		  		+ "fecha_modificacion,"
		  		+ "usuario_modificacion, "
		  		+ "estacion_modificacion, "
		  		+ "codigo_cliente,  "
		  		
		  		+ "codigo_tipo_domicilio, "
		  		+ "nro_domicilio,"
		  		+ "dpto_int_domicilio, "
		  		+ "referencia, "
		  		+ "urbanizacion, "
		  		
		  		+ "latitud, "
		  		+ "longitud";
		
		// 3,2,now(), now(), 50, 1, 'av los rosales 1245', 120108 ,  now(), user,inet_server_addr(), now(),user,inet_server_addr(), 3
		String insert = "insert into contrato_servicio("+campos+" ) values ";

		
		int numero  = 1;
		for(Point point :  getPoins()){
		
			Double lat =  point.getX();
			Double lon =  point.getY();
			String direccion =  getDirecciones().get(Util.randInt(0, getDirecciones().size()-1));
			Integer codigoDistrito =  getDistrito().get(Util.randInt(0, getDistrito().size()-1)).getCodigoDistrito();
			Integer codigoCliente =  Util.randInt(1, 1);
			String values  =  "("+numero+",now(),now(),50.0,1,'"+direccion+"',"+codigoDistrito+",  "
					+ " now(), user,inet_server_addr(), now(),user,inet_server_addr(),"+codigoCliente+",1,null, null, null, null,"+lat +","+lon+") ;";
			System.out.println( insert + values);
			
			numero++;
			
		}		
		
	}
	

	public static List<Point> getPoins(){		
		List<Point> points =   new ArrayList<>();
		
		
		points.add(new Point(-12.02336,-75.18149));
		points.add(new Point(-12.06944,-75.19765));
		points.add(new Point(-12.06915,-75.21164));
		points.add(new Point(-12.06179,-75.20109));
		points.add(new Point(-12.05854,-75.20155));
		points.add(new Point(-12.06413,-75.20797));
		points.add(new Point(-12.07823,-75.21877));
		points.add(new Point(-12.04212,-75.19402));
		points.add(new Point(-12.07116,-75.20408));
		points.add(new Point(-12.05215,-75.18362));
		points.add(new Point(-12.03123,-75.18910));
		points.add(new Point(-12.06826,-75.19994));
		points.add(new Point(-12.06463,-75.20019));
		points.add(new Point(-12.05499,-75.20047));
		points.add(new Point(-12.03113,-75.18500));
		points.add(new Point(-12.08257,-75.22561));
		points.add(new Point(-12.04064,-75.19948));
		points.add(new Point(-12.06151,-75.18277));
		points.add(new Point(-12.04729,-75.20137));
		points.add(new Point(-12.03024,-75.19002));
		points.add(new Point(-12.06843,-75.19358));
		points.add(new Point(-12.02153,-75.17988));
		points.add(new Point(-12.06105,-75.20912));
		points.add(new Point(-12.06356,-75.20669));
		points.add(new Point(-12.08248,-75.22927));
		points.add(new Point(-12.06363,-75.19824));
		points.add(new Point(-12.04060,-75.19748));
		points.add(new Point(-12.06917,-75.19917));
		points.add(new Point(-12.05264,-75.19266));
		points.add(new Point(-12.07861,-75.21315));
		points.add(new Point(-12.06228,-75.18725));
		points.add(new Point(-12.05394,-75.18663));
		points.add(new Point(-12.01667,-75.18195));
		points.add(new Point(-12.04618,-75.19829));
		points.add(new Point(-12.04793,-75.19440));
		points.add(new Point(-12.05345,-75.18949));
		points.add(new Point(-12.05652,-75.18775));
		points.add(new Point(-12.06120,-75.18302));
		points.add(new Point(-12.06979,-75.20452));
		points.add(new Point(-12.04953,-75.19943));
		points.add(new Point(-12.01531,-75.18356));
		points.add(new Point(-12.08162,-75.22686));
		points.add(new Point(-12.06254,-75.18474));
		points.add(new Point(-12.04143,-75.19373));
		points.add(new Point(-12.07646,-75.21016));
		points.add(new Point(-12.05916,-75.19782));
		points.add(new Point(-12.07562,-75.21807));
		points.add(new Point(-12.04960,-75.19490));
		points.add(new Point(-12.07021,-75.19895));
		points.add(new Point(-12.03816,-75.18825));
		points.add(new Point(-12.02902,-75.18368));
		points.add(new Point(-12.02271,-75.18158));
		points.add(new Point(-12.07034,-75.20123));
		points.add(new Point(-12.04101,-75.18754));
		points.add(new Point(-12.06397,-75.18773));
		points.add(new Point(-12.08586,-75.22851));
		points.add(new Point(-12.04926,-75.19908));
		points.add(new Point(-12.05934,-75.19672));
		points.add(new Point(-12.02947,-75.19635));
		points.add(new Point(-12.03919,-75.19942));
		points.add(new Point(-12.04575,-75.19772));
		points.add(new Point(-12.02363,-75.18146));
		points.add(new Point(-12.08173,-75.22262));
		points.add(new Point(-12.08221,-75.23156));
		points.add(new Point(-12.04334,-75.19482));
		points.add(new Point(-12.05699,-75.20459));
		points.add(new Point(-12.06962,-75.19706));
		points.add(new Point(-12.04725,-75.19219));
		points.add(new Point(-12.01748,-75.18721));
		points.add(new Point(-12.05019,-75.18952));
		points.add(new Point(-12.04785,-75.19884));
		points.add(new Point(-12.01724,-75.18151));
		points.add(new Point(-12.03292,-75.18635));
		points.add(new Point(-12.07569,-75.21036));
		points.add(new Point(-12.06115,-75.20932));
		points.add(new Point(-12.08256,-75.23132));
		points.add(new Point(-12.06256,-75.20882));
		points.add(new Point(-12.06862,-75.20721));
		points.add(new Point(-12.07688,-75.22175));
		points.add(new Point(-12.05869,-75.20589));
		points.add(new Point(-12.08314,-75.22961));
		points.add(new Point(-12.07865,-75.21024));
		points.add(new Point(-12.06491,-75.18777));
		points.add(new Point(-12.08298,-75.22098));
		points.add(new Point(-12.06188,-75.18267));
		points.add(new Point(-12.02713,-75.18420));
		points.add(new Point(-12.07294,-75.23120));
		points.add(new Point(-12.03218,-75.19608));
		points.add(new Point(-12.03767,-75.19607));
		points.add(new Point(-12.03242,-75.19157));
		points.add(new Point(-12.03862,-75.19000));
		points.add(new Point(-12.06409,-75.19359));
		points.add(new Point(-12.06812,-75.19311));
		points.add(new Point(-12.07602,-75.20884));
		points.add(new Point(-12.07575,-75.22795));
		points.add(new Point(-12.07163,-75.21887));
		points.add(new Point(-12.06997,-75.20722));
		points.add(new Point(-12.04551,-75.19215));
		points.add(new Point(-12.05323,-75.18847));
		points.add(new Point(-12.05957,-75.18714));
		points.add(new Point(-12.04526,-75.19182));
		points.add(new Point(-12.02397,-75.19226));
		points.add(new Point(-12.08227,-75.22085));
		points.add(new Point(-12.04805,-75.18849));
		points.add(new Point(-12.08301,-75.22077));
		points.add(new Point(-12.06308,-75.19478));
		points.add(new Point(-12.06110,-75.19062));
		points.add(new Point(-12.02569,-75.19194));
		points.add(new Point(-12.06514,-75.20942));
		points.add(new Point(-12.06066,-75.19317));
		points.add(new Point(-12.03443,-75.19016));
		points.add(new Point(-12.03076,-75.19300));
		points.add(new Point(-12.08000,-75.22760));
		points.add(new Point(-12.08450,-75.22977));
		points.add(new Point(-12.05889,-75.19503));
		points.add(new Point(-12.02710,-75.19454));
		points.add(new Point(-12.05923,-75.19619));
		points.add(new Point(-12.02272,-75.17916));
		points.add(new Point(-12.05117,-75.19090));
		points.add(new Point(-12.07400,-75.22715));
		points.add(new Point(-12.07524,-75.21302));
		points.add(new Point(-12.04767,-75.19102));
		points.add(new Point(-12.02649,-75.18713));
		points.add(new Point(-12.02857,-75.18615));
		points.add(new Point(-12.07657,-75.21525));
		points.add(new Point(-12.07081,-75.20980));
		points.add(new Point(-12.07432,-75.21143));
		points.add(new Point(-12.07462,-75.21832));
		points.add(new Point(-12.06225,-75.20988));
		points.add(new Point(-12.06665,-75.21251));
		points.add(new Point(-12.07503,-75.21514));
		points.add(new Point(-12.01871,-75.18006));
		points.add(new Point(-12.03220,-75.19051));
		points.add(new Point(-12.05995,-75.19541));
		points.add(new Point(-12.02809,-75.19474));
		points.add(new Point(-12.06053,-75.20959));
		points.add(new Point(-12.06839,-75.21594));
		points.add(new Point(-12.06305,-75.18340));
		points.add(new Point(-12.05822,-75.18884));
		points.add(new Point(-12.05458,-75.18394));
		points.add(new Point(-12.06507,-75.20470));
		points.add(new Point(-12.05144,-75.18437));
		points.add(new Point(-12.03867,-75.19100));
		points.add(new Point(-12.06625,-75.20518));
		points.add(new Point(-12.03999,-75.19314));
		points.add(new Point(-12.05255,-75.20315));
		points.add(new Point(-12.06542,-75.19369));
		points.add(new Point(-12.06708,-75.19474));
		points.add(new Point(-12.08026,-75.22130));
		points.add(new Point(-12.06486,-75.21349));
		points.add(new Point(-12.05425,-75.19466));
		points.add(new Point(-12.03584,-75.19638));
		points.add(new Point(-12.08488,-75.22783));
		points.add(new Point(-12.05956,-75.18386));
		points.add(new Point(-12.07326,-75.20663));
		points.add(new Point(-12.07653,-75.21267));
		points.add(new Point(-12.02483,-75.18605));
		points.add(new Point(-12.07317,-75.21635));
		points.add(new Point(-12.06237,-75.19562));
		points.add(new Point(-12.05206,-75.20171));
		points.add(new Point(-12.06070,-75.20131));
		points.add(new Point(-12.04145,-75.19760));
		points.add(new Point(-12.07107,-75.19799));
		points.add(new Point(-12.06106,-75.18538));
		points.add(new Point(-12.05054,-75.20283));
		points.add(new Point(-12.06950,-75.19456));
		points.add(new Point(-12.01742,-75.18032));
		points.add(new Point(-12.07614,-75.22934));
		points.add(new Point(-12.06674,-75.21274));
		points.add(new Point(-12.02950,-75.18438));
		points.add(new Point(-12.05079,-75.18688));
		points.add(new Point(-12.03306,-75.18914));
		points.add(new Point(-12.02818,-75.18601));
		points.add(new Point(-12.04941,-75.19940));
		points.add(new Point(-12.07107,-75.22482));
		points.add(new Point(-12.03214,-75.19534));
		points.add(new Point(-12.07580,-75.21241));
		points.add(new Point(-12.02599,-75.18874));
		points.add(new Point(-12.04277,-75.19692));
		points.add(new Point(-12.07999,-75.22327));
		points.add(new Point(-12.07267,-75.21899));
		points.add(new Point(-12.08496,-75.22655));
		points.add(new Point(-12.03120,-75.19209));
		points.add(new Point(-12.07625,-75.21669));
		points.add(new Point(-12.06822,-75.19694));
		points.add(new Point(-12.07307,-75.20792));
		points.add(new Point(-12.06198,-75.20537));
		points.add(new Point(-12.07570,-75.20796));
		points.add(new Point(-12.06324,-75.19657));
		points.add(new Point(-12.05958,-75.19793));
		points.add(new Point(-12.08025,-75.22183));
		points.add(new Point(-12.06746,-75.21532));
		points.add(new Point(-12.03919,-75.19482));
		points.add(new Point(-12.04185,-75.19091));
		points.add(new Point(-12.07361,-75.22094));
		points.add(new Point(-12.06947,-75.21818));
		points.add(new Point(-12.07911,-75.21198));
		points.add(new Point(-12.04167,-75.20003));
		points.add(new Point(-12.03383,-75.18928));
		points.add(new Point(-12.06075,-75.19223));
		points.add(new Point(-12.02237,-75.19126));
		points.add(new Point(-12.05538,-75.20568));
		points.add(new Point(-12.06310,-75.19732));
		points.add(new Point(-12.06545,-75.19854));
		points.add(new Point(-12.06377,-75.18771));
		points.add(new Point(-12.05644,-75.20482));
		points.add(new Point(-12.04224,-75.19493));
		points.add(new Point(-12.04805,-75.18739));
		points.add(new Point(-12.05233,-75.20319));
		points.add(new Point(-12.06753,-75.19224));
		points.add(new Point(-12.06428,-75.21051));
		points.add(new Point(-12.07223,-75.21626));
		points.add(new Point(-12.04662,-75.19079));
		points.add(new Point(-12.06238,-75.19339));
		points.add(new Point(-12.05992,-75.18815));
		points.add(new Point(-12.06010,-75.20142));
		points.add(new Point(-12.02539,-75.18751));
		points.add(new Point(-12.05178,-75.19393));
		points.add(new Point(-12.04489,-75.19778));
		points.add(new Point(-12.05216,-75.18494));
		points.add(new Point(-12.08121,-75.23104));
		points.add(new Point(-12.05523,-75.19753));
		points.add(new Point(-12.05457,-75.19591));
		points.add(new Point(-12.07207,-75.21695));
		points.add(new Point(-12.04918,-75.18473));
		points.add(new Point(-12.07112,-75.19893));
		points.add(new Point(-12.07610,-75.22162));
		points.add(new Point(-12.04332,-75.19650));
		points.add(new Point(-12.03687,-75.19917));
		points.add(new Point(-12.07292,-75.20641));
		points.add(new Point(-12.01946,-75.18444));
		points.add(new Point(-12.05077,-75.19629));
		points.add(new Point(-12.05981,-75.20511));
		points.add(new Point(-12.01931,-75.17943));
		points.add(new Point(-12.04154,-75.19257));
		points.add(new Point(-12.05389,-75.19910));
		points.add(new Point(-12.07726,-75.22791));
		points.add(new Point(-12.05207,-75.19948));
		points.add(new Point(-12.04936,-75.19615));
		points.add(new Point(-12.03567,-75.18854));
		points.add(new Point(-12.06521,-75.21334));
		points.add(new Point(-12.05265,-75.18848));
		points.add(new Point(-12.04909,-75.19518));
		points.add(new Point(-12.02385,-75.18688));
		points.add(new Point(-12.04528,-75.19686));
		points.add(new Point(-12.04906,-75.19860));
		points.add(new Point(-12.07441,-75.22906));
		points.add(new Point(-12.04916,-75.19159));
		points.add(new Point(-12.02550,-75.19302));
		points.add(new Point(-12.08349,-75.22909));
		points.add(new Point(-12.02526,-75.18780));
		points.add(new Point(-12.03951,-75.18904));
		points.add(new Point(-12.05265,-75.20470));
		points.add(new Point(-12.06814,-75.20511));
		points.add(new Point(-12.01549,-75.17772));
		points.add(new Point(-12.03076,-75.19727));
		points.add(new Point(-12.05726,-75.18620));
		points.add(new Point(-12.06657,-75.20334));
		points.add(new Point(-12.07585,-75.23229));
		points.add(new Point(-12.05601,-75.18802));
		points.add(new Point(-12.06225,-75.20141));
		points.add(new Point(-12.05643,-75.18812));
		points.add(new Point(-12.07080,-75.21970));
		points.add(new Point(-12.06496,-75.20192));
		points.add(new Point(-12.07154,-75.22687));
		points.add(new Point(-12.01514,-75.17651));
		points.add(new Point(-12.02251,-75.18834));
		points.add(new Point(-12.07610,-75.20963));
		points.add(new Point(-12.04590,-75.18933));
		points.add(new Point(-12.05860,-75.18811));
		points.add(new Point(-12.07565,-75.21949));
		points.add(new Point(-12.03365,-75.18926));
		points.add(new Point(-12.04809,-75.19089));
		points.add(new Point(-12.08208,-75.22139));
		points.add(new Point(-12.05540,-75.19866));
		points.add(new Point(-12.01767,-75.18120));
		points.add(new Point(-12.06737,-75.21089));
		points.add(new Point(-12.02479,-75.18243));
		points.add(new Point(-12.02532,-75.19103));
		points.add(new Point(-12.03581,-75.19641));
		points.add(new Point(-12.07990,-75.21843));
		points.add(new Point(-12.03906,-75.19384));
		points.add(new Point(-12.08138,-75.22684));
		points.add(new Point(-12.05893,-75.18179));
		points.add(new Point(-12.05507,-75.19941));
		points.add(new Point(-12.06595,-75.18969));
		points.add(new Point(-12.05780,-75.19056));
		points.add(new Point(-12.04334,-75.18982));
		points.add(new Point(-12.01630,-75.18441));
		points.add(new Point(-12.07632,-75.20933));
		points.add(new Point(-12.04480,-75.19097));
		points.add(new Point(-12.06173,-75.18105));
		points.add(new Point(-12.08044,-75.22050));
		points.add(new Point(-12.03815,-75.19270));
		points.add(new Point(-12.07432,-75.21998));
		points.add(new Point(-12.03017,-75.19331));
		points.add(new Point(-12.03558,-75.19414));
		points.add(new Point(-12.05780,-75.20259));
		points.add(new Point(-12.03460,-75.19247));
		points.add(new Point(-12.07478,-75.20358));
		points.add(new Point(-12.06715,-75.19591));
		points.add(new Point(-12.06318,-75.20453));
		points.add(new Point(-12.06221,-75.19107));
		points.add(new Point(-12.06631,-75.20872));
		points.add(new Point(-12.04916,-75.20308));
		points.add(new Point(-12.03739,-75.19656));
		points.add(new Point(-12.06585,-75.20713));
		points.add(new Point(-12.05912,-75.19894));
		points.add(new Point(-12.08443,-75.22481));
		points.add(new Point(-12.06209,-75.18801));
		points.add(new Point(-12.05624,-75.18277));
		points.add(new Point(-12.07299,-75.21882));
		points.add(new Point(-12.03034,-75.18639));
		points.add(new Point(-12.03377,-75.18867));
		points.add(new Point(-12.06917,-75.21055));
		points.add(new Point(-12.05161,-75.19335));
		points.add(new Point(-12.06357,-75.20339));
		points.add(new Point(-12.05271,-75.19339));
		points.add(new Point(-12.04260,-75.19998));
		points.add(new Point(-12.04598,-75.19101));
		points.add(new Point(-12.04690,-75.19300));
		points.add(new Point(-12.07473,-75.20832));
		points.add(new Point(-12.04510,-75.19047));
		points.add(new Point(-12.06583,-75.21030));
		points.add(new Point(-12.03800,-75.19923));
		points.add(new Point(-12.05335,-75.18339));
		points.add(new Point(-12.05574,-75.18991));
		points.add(new Point(-12.07118,-75.20345));
		points.add(new Point(-12.06720,-75.20994));
		points.add(new Point(-12.07453,-75.22746));
		points.add(new Point(-12.05883,-75.18902));
		points.add(new Point(-12.03039,-75.18884));
		points.add(new Point(-12.02720,-75.18842));
		points.add(new Point(-12.02103,-75.18834));
		points.add(new Point(-12.01932,-75.18574));
		points.add(new Point(-12.07946,-75.21723));
		points.add(new Point(-12.04690,-75.19003));
		points.add(new Point(-12.01761,-75.18272));
		points.add(new Point(-12.08375,-75.22990));
		points.add(new Point(-12.01851,-75.18668));
		points.add(new Point(-12.07743,-75.22760));
		points.add(new Point(-12.04646,-75.18663));
		points.add(new Point(-12.04817,-75.19658));
		points.add(new Point(-12.02501,-75.19168));
		points.add(new Point(-12.06315,-75.20101));
		points.add(new Point(-12.05187,-75.19160));
		points.add(new Point(-12.06592,-75.20555));
		points.add(new Point(-12.04619,-75.18684));
		points.add(new Point(-12.06743,-75.20044));
		points.add(new Point(-12.02336,-75.18233));
		points.add(new Point(-12.06811,-75.20063));
		points.add(new Point(-12.04990,-75.18826));
		points.add(new Point(-12.06933,-75.19456));
		points.add(new Point(-12.07467,-75.20850));
		points.add(new Point(-12.06160,-75.19419));
		points.add(new Point(-12.07913,-75.23007));
		points.add(new Point(-12.06318,-75.18926));
		points.add(new Point(-12.04817,-75.18866));
		points.add(new Point(-12.06272,-75.20613));
		points.add(new Point(-12.05260,-75.19275));
		points.add(new Point(-12.05855,-75.19036));
		points.add(new Point(-12.05596,-75.20222));
		points.add(new Point(-12.05107,-75.18790));
		points.add(new Point(-12.06259,-75.19615));
		points.add(new Point(-12.04972,-75.19400));
		points.add(new Point(-12.07278,-75.22870));
		points.add(new Point(-12.05552,-75.18984));
		points.add(new Point(-12.04955,-75.18440));
		points.add(new Point(-12.03170,-75.18809));
		points.add(new Point(-12.02822,-75.19271));
		points.add(new Point(-12.07311,-75.21592));
		points.add(new Point(-12.06000,-75.18384));
		points.add(new Point(-12.06141,-75.20530));
		points.add(new Point(-12.07578,-75.21198));
		points.add(new Point(-12.02992,-75.19613));
		points.add(new Point(-12.06829,-75.20595));
		points.add(new Point(-12.07267,-75.22788));
		points.add(new Point(-12.04774,-75.19921));
		points.add(new Point(-12.06282,-75.20302));
		points.add(new Point(-12.05935,-75.19437));
		points.add(new Point(-12.04408,-75.19423));
		points.add(new Point(-12.05812,-75.20303));
		points.add(new Point(-12.06954,-75.20150));
		points.add(new Point(-12.08005,-75.21773));
		points.add(new Point(-12.07674,-75.22209));
		points.add(new Point(-12.04977,-75.19299));
		points.add(new Point(-12.06013,-75.19387));
		points.add(new Point(-12.06356,-75.18718));
		points.add(new Point(-12.04253,-75.19170));
		points.add(new Point(-12.06177,-75.19221));
		points.add(new Point(-12.01856,-75.17729));
		points.add(new Point(-12.03079,-75.19158));
		points.add(new Point(-12.06195,-75.20663));
		points.add(new Point(-12.07636,-75.20926));
		points.add(new Point(-12.03508,-75.18764));
		points.add(new Point(-12.03944,-75.20029));
		points.add(new Point(-12.06102,-75.18126));
		points.add(new Point(-12.03073,-75.19344));
		points.add(new Point(-12.01512,-75.18315));
		points.add(new Point(-12.07430,-75.22204));
		points.add(new Point(-12.04298,-75.19098));
		points.add(new Point(-12.06044,-75.18501));
		points.add(new Point(-12.06205,-75.20896));
		points.add(new Point(-12.02535,-75.18057));
		points.add(new Point(-12.06353,-75.20237));
		points.add(new Point(-12.06695,-75.21352));
		points.add(new Point(-12.04783,-75.19039));
		points.add(new Point(-12.03018,-75.18573));
		points.add(new Point(-12.03189,-75.18631));
		points.add(new Point(-12.06908,-75.21310));
		points.add(new Point(-12.06064,-75.20114));
		points.add(new Point(-12.04601,-75.18704));
		points.add(new Point(-12.06671,-75.20642));
		points.add(new Point(-12.05811,-75.18415));
		points.add(new Point(-12.05586,-75.19642));
		points.add(new Point(-12.03142,-75.18945));
		points.add(new Point(-12.06991,-75.19532));
		points.add(new Point(-12.01450,-75.17779));
		points.add(new Point(-12.03312,-75.18601));
		points.add(new Point(-12.07080,-75.20579));
		points.add(new Point(-12.04654,-75.20241));
		points.add(new Point(-12.06322,-75.18621));
		points.add(new Point(-12.04235,-75.19364));
		points.add(new Point(-12.01996,-75.18363));
		points.add(new Point(-12.02792,-75.18422));
		points.add(new Point(-12.07289,-75.22012));
		points.add(new Point(-12.04866,-75.20277));
		points.add(new Point(-12.06050,-75.20024));
		points.add(new Point(-12.05492,-75.19219));
		points.add(new Point(-12.06252,-75.20500));
		points.add(new Point(-12.02390,-75.18715));
		points.add(new Point(-12.06366,-75.19904));
		points.add(new Point(-12.03492,-75.19898));
		points.add(new Point(-12.08185,-75.22113));
		points.add(new Point(-12.08491,-75.22731));
		points.add(new Point(-12.01321,-75.18178));
		points.add(new Point(-12.06808,-75.19420));
		points.add(new Point(-12.05822,-75.19058));
		points.add(new Point(-12.07881,-75.22823));
		points.add(new Point(-12.06519,-75.19679));
		points.add(new Point(-12.05639,-75.18935));
		points.add(new Point(-12.02249,-75.18014));
		points.add(new Point(-12.01975,-75.18911));
		points.add(new Point(-12.02523,-75.19132));
		points.add(new Point(-12.06101,-75.19676));
		points.add(new Point(-12.06468,-75.19947));
		points.add(new Point(-12.07614,-75.21424));
		points.add(new Point(-12.04024,-75.19559));
		points.add(new Point(-12.06850,-75.21165));
		points.add(new Point(-12.01434,-75.18176));
		points.add(new Point(-12.02299,-75.17996));
		points.add(new Point(-12.01814,-75.18528));

		
		
		return  points;
		
	}
	
	
	
	public static List<String> getDirecciones(){		
		List<String> direcciones =   new ArrayList<>();

		
		direcciones.add("Avenida Giráldez, 107");
		direcciones.add("Calle Cedros Mz. F Lt. 12 - Urb. La Merced");
		direcciones.add("Pasaje Alfaro, 105 - 2° Of. 201 - El Tambo - Huancayo");
		direcciones.add("Jirón Arequipa, 783");
		direcciones.add("Jirón San José, 210 - Urb. San Carlos");
		direcciones.add("Avenida Huancavelica, 2294 - El Tambo");
		direcciones.add("Alameda Ángel Mercedes, 2000");
		direcciones.add("Avenida Calmell del Solar, 719 - San Carlos");
		direcciones.add("Jirón Cosmos, Mz. C Lt. 5 - Urb. Co Juan Goyzueta ");
		direcciones.add("Avenida Jacinto Ibarra, 150");
		direcciones.add("Pasaje Alfaro, 105 - 2° Of. 201 - El Tambo - Huancayo");
		direcciones.add("Calle Real, 341");
		direcciones.add("Pto.2 - 18 - ME - Virgen de la Merced Pto.2 - 18");
		direcciones.add("Avenida Ferrocarril, 460");
		direcciones.add("Puno Nro 430");
		direcciones.add("Jr Moquegua Nro 873  Nd Cercado Huancayo");
		direcciones.add("Jr Libertad Nro 185");
		direcciones.add("Jirón Ancash, 288");
		direcciones.add("Pasaje Montevideo , 185  - Urb. Miraflores");
		direcciones.add("Jirón Alejandro O Deustua, 1350 - El Tambo ");
		direcciones.add("Av Giraldes Nro 634  Nd Cercado Huancayo");
		direcciones.add("Av Real Chilca Nro 555");

		return direcciones;
	}
	
	
	
	
	public static List<Distrito> getDistrito(){		
		List<Distrito> distritos =   new ArrayList<>();
		
		distritos.add(new Distrito(120108,"Chongos Alto"));
		distritos.add(new Distrito(120116,"HUACRAPUQUIO"));
		distritos.add(new Distrito(120117,"HUALHUAS"));
		distritos.add(new Distrito(120126,"PUCARA"));
		distritos.add(new Distrito(120128,"QUILCAS"));
		distritos.add(new Distrito(120136,"VIQUES"));

		return distritos;
	}

	
	
	
	
	
	
	

}
