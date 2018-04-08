
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCuMB--kvTJG29_maJlBDUfEAuYW8_K50M&sensor=false">
</script>
 
<script>

//所有坐标

/* var locations = new Array( "31.953313,121.841581",
							"31.15347,121.291706", 
							"31.207516,121.412556",
							"31.122909,121.458561", 
							"31.118207,121.38715",
							"31.041168,121.426289",
							"39.9289,116.3883",
							"30.985262,121.301319",
							"31.057934,121.305834"); */
var obj = eval('${obj}');
var locations = [];
for(var i=0;i<obj.length;i++){
	var single = obj[i];
	locations[i]=single.latitude+","+single.longitude;
}
//var locations = '${obj}';


function initialize() { 
		/* var latlng = new google.maps.LatLng(31.253313, 121.241581);  */
		var latlng = new google.maps.LatLng(31.253313, 121.241581);  
		var myOptions ={ 
				zoom: 10, //缩放级别
				center: latlng, //坐标
				mapTypeId: google.maps.MapTypeId.ROADMAP //类型:默认的普通二维图块
		}; 
		map = new google.maps.Map(document.getElementById("googleMap"), myOptions); //线条设置

		var polyOptions ={ 
					strokeColor: '#FF0000', //颜色
					strokeOpacity: 1.0, //透明度
					strokeWeight: 4 //宽度
				} 
		poly = new google.maps.Polyline(polyOptions);
		/* poly.setMap(map); //装载 */

		/*循环标出所有坐标 */
		for(var i=0; i<locations.length; i++){ 
			var loc = locations[i].split(','); 
			var path = poly.getPath(); //获取线条的坐标
		    path.push(new google.maps.LatLng(loc[0], loc[1])); //为线条添加标记坐标
		//生成标记图标
			marker = new google.maps.Marker({
				position: new google.maps.LatLng(loc[0], loc[1]), 
				map: map, 
				icon: "http://labs.google.com/ridefinder/images/mm_20_green.png"
			}); 
		} 
		
}
google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
 
<div id="googleMap" style="width:1700px;height:800px;"></div>
 
