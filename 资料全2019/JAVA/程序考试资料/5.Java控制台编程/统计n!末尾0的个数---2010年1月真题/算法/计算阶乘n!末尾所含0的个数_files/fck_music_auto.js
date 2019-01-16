(function(){
//fck init music
function EnP(s){var obj={},p=s.split("&");for(var i=0,n=p.length;i<n;i++){var t=p[i].split("=");obj[t[0]]=t[1]}return obj;}
function DeP(s){var u="";for(var i in s){u+='&'+encodeURIComponent(i)+'='+encodeURIComponent(s[i]);}return u;}
if(window.Node){Node.prototype.replaceNode=function(Node){this.parentNode.replaceChild(Node,this);}}
var isfir=true,imgBox=document.getElementsByName('FCK_MP3_MUSIC');
//是否是第一个自动播放
var mp3URL="http://box.baidu.com/widget/flash/bdspacesong.swf";

for(var i=0,n=imgBox.length;i<n;i++){
	var img=imgBox[i];
	if(img.getAttribute('rel')){
		var musicDiv = document.createElement("SPAN");
        var obj=EnP(decodeURIComponent(img.getAttribute('rel').replace(/[\s><()'"]+/g,'')));
		img.replaceNode(musicDiv);
        if(obj.autoPlay=='true' && isfir){
			isfir=false;
			obj.autoPlay=true;
		}else{
			obj.autoPlay=false;
		}

        /*****for detail****/
        var hr=location.href;
        if(     (hr.lastIndexOf('/blog')+5) == hr.length || (hr.lastIndexOf('/blog/')+6) == hr.length    ){
            obj.autoPlay=false;
        }
        baidu.swf.create(
			{
				'id'		:"mp3player"+Math.random(),
				'width' 	:"400",
				'height'	:"95",
				'ver'		:"9.0.0.0",
				'url'		:mp3URL+'?'+DeP(obj),
				'wmode'		:"opaque",
                'allowScriptAccess':"always",
                'allowNetworking ' :"all"
			},
			musicDiv
		);
		i--;n--;
	}
}
})();
