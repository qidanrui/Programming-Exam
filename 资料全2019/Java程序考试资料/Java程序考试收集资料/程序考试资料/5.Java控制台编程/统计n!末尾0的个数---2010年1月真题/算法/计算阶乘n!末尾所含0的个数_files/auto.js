function sendStat(s){new Image().src="http://hi.baidu.com/sys/statlog/1.gif?m="+s+"&v="+s+"&t="+Math.random();}

var CookieBoy={
	setCookie:function(key,value){
		if(top.location.href.indexOf('modify/aboutme')>-1)return;
  		document.cookie = key + "="+ escape(value)+ ";expires=Wed, 28-Nov-37 01:45:46 GMT;path=/;domain=hi.baidu.com";
	},
	getCookie:function(key){
	  var a, reg = new RegExp("(^| )" + key + "=([^;]*)(;|$)");
	  if(a = document.cookie.match(reg))
  		return unescape(a[2]);
	  return null;
	}
}


var popHtml='<div style="width:425px;height:355x; background:url(http://act.hi.baidu.com/mod/flash/popreg.gif) no-repeat"><div style="width:425px;height:35px;"><div title="关闭" style="width:35px;height:35px;float:right;cursor:pointer" onclick="SCD.closeReg()"></div></div><div style="width:425px;height:320px;"><a style="display:block;cursor:pointer;width:160px;height:50px;text-align:center;margin:232px 120px 0 137px;background:url(http://img.baidu.com/hi/default/0.gif)" target="_blank" onclick="SCD.closeReg(1)" title="注册百度空间" href="http://hi.baidu.com/st/reg.html">&nbsp;</a></div></div>';
var SCD={
	regURL:"",
	swfURL:"http://act.hi.baidu.com/mod/flash/popreg.swf",
	SCDPOP:null,
	SCDREG:null,
	SCDMASK:null,
	init:function(refer){return;

		if(refer&&refer.indexOf('http://www.baidu.com')==0){

			if(CookieBoy.getCookie('POP')){
				if(CookieBoy.getCookie('POP')=='0'){
					return;
				}
			}else{
				CookieBoy.setCookie('POP','1');
			}
			
			if(CookieBoy.getCookie('BDUSS')){
				return;
			}

                        sendStat('go_icon');
			this.createUI();
			var m=this;
			m.setStatus('SCDPOP',1);
			m.setStatus('SCDMASK',1);
			m.setLocation('SCDPOP');
			m.setLocation('SCDMASK');
		}
	}
	,createUI:function(){
		if (/*@cc_on!@*/0){
			window.attachEvent("onresize",function(){SCD.setLocation('SCDPOP');SCD.setLocation('SCDREG');SCD.setLocation('SCDMASK');})


		}else{
			window.addEventListener("resize",function(){SCD.setLocation('SCDPOP');SCD.setLocation('SCDREG');SCD.setLocation('SCDMASK');},false);

		}
		var m=this;
		var SCDPOP	=	document.createElement("DIV");
		SCDPOP['style']['width']	='100px';
		SCDPOP['style']['height']	='100px';
		SCDPOP['style']['display']	='none';
		SCDPOP['style']['cursor']	='pointer';
		SCDPOP['id']='SCDPOP';
		document.body.appendChild(SCDPOP);
		SCDPOP.innerHTML='<object width="150" height="150" align="middle" id="SCDUI" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"><param value="transparent" name="wmode"><param value="'+this.swfURL+'" name="movie"><embed width="150" height="150" align="middle" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" name="SCDUI" src="'+this.swfURL+'" wmode="transparent"></object>';
		SCDPOP.onclick=function(){m.closePop()}

		var SCDREG	=	document.createElement("DIV");
		SCDREG['style']['width']	='420px';
		SCDREG['style']['height']	='360px';
		SCDREG['style']['display']	='none';
		SCDREG['id']='SCDREG';
		SCDREG.innerHTML=popHtml;
		document.body.appendChild(SCDREG);

		var SCDMASK	=	document.createElement("DIV");
		SCDMASK['style']['width']	='120px';
		SCDMASK['style']['height']	='120px';
		SCDMASK['style']['display']	='none';
		SCDMASK['style']['cursor']	='pointer';
		SCDMASK['id']='SCDMASK';
		document.body.appendChild(SCDMASK);
		if (/*@cc_on!@*/0){
			SCDMASK.innerHTML='<div style="width:100%;height:100%;background:url(http://img.baidu.com/hi/default/0.gif)">&nbsp;</div>';
		}
		SCDMASK.onclick=function(){m.closePop()}
		
		
		this.SCDPOP=SCDPOP;
		this.SCDREG=SCDREG;
		this.SCDMASK=SCDMASK;
	}
	,E:function(id){
		return document.getElementById(id);
	}
	,setStatus:function(id,status){
		this[id]['style']['display']=status?'':'none';
	}
	,setLocation:function(id,type){
		var panel=this[id];
		panel['style']['position']	="absolute";
		if(type==1)	panel['style']['display']	='';
		var sClientWidth=document.body.clientWidth;
		var sClientHeight=document.body.clientHeight;
		var sScrollTop=document.body.scrollTop;
		var sleft=(document.body.clientWidth/2)-(panel.offsetWidth/2);
		var iTop=-80+(sClientHeight/2+sScrollTop)-(panel.offsetHeight/2);
		var sTop=iTop>0?iTop:(sClientHeight/2+sScrollTop)-(panel.offsetHeight/2);
		if(sTop<1)sTop="20";
		if(sleft<1)sleft="20";
		if(id=='SCDPOP'||id=='SCDMASK'){
			sleft=sleft+sClientWidth/5;
			//sTop=this.E('blog_text').offsetTop+20;
		}
		if(id=='SCDREG'){
			//sTop=this.E('blog_text').offsetTop;
		}
		panel['style']['left']=sleft+'px';
		panel['style']['top']=sTop+'px';
		if(id=='SCDMASK')
			panel['style']['zIndex']=65531;
		else
			panel['style']['zIndex']=65530;
	}
	,closePop:function(){
		this.setStatus('SCDPOP',0);
		this.setStatus('SCDMASK',0);
		this.setLocation('SCDREG',1);
		CookieBoy.setCookie('POP','0');
		sendStat('go_panel');
	}
	,closeReg:function(n){
		this.setStatus('SCDREG',0);
		CookieBoy.setCookie('POP','0');
		if(n==1)sendStat('go_reg');

	}
};



