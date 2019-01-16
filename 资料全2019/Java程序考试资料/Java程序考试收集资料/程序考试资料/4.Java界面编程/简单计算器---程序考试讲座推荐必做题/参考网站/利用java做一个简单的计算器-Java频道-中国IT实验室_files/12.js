function ObjectAD() {
  /* Define Variables*/
  this.ADID        = 0;
  this.ADType      = 0;
  this.ADName      = "";
  this.ImgUrl      = "";
  this.ImgWidth    = 0;
  this.ImgHeight   = 0;
  this.FlashWmode  = 0;
  this.LinkUrl     = "";
  this.LinkTarget  = 0;
  this.LinkAlt     = "";
  this.Priority    = 0;
  this.CountView   = 0;
  this.CountClick  = 0;
  this.InstallDir  = "";
  this.ADDIR       = "";
}

function CodeZoneAD(_id) {
  /* Define Common Variables*/
  this.ID          = _id;
  this.ZoneID      = 0;

  /* Define Unique Variables*/

  /* Define Objects */
  this.AllAD       = new Array();
  this.ShowAD      = null;

  /* Define Functions */
  this.AddAD       = CodeZoneAD_AddAD;
  this.GetShowAD   = CodeZoneAD_GetShowAD;
  this.Show        = CodeZoneAD_Show;

}

function CodeZoneAD_AddAD(_AD) {
  this.AllAD[this.AllAD.length] = _AD;
}

function CodeZoneAD_GetShowAD() {
  if (this.ShowType > 1) {
    this.ShowAD = this.AllAD[0];
    return;
  }
  var num = this.AllAD.length;
  var sum = 0;
  for (var i = 0; i < num; i++) {
    sum = sum + this.AllAD[i].Priority;
  }
  if (sum <= 0) {return ;}
  var rndNum = Math.random() * sum;
  i = 0;
  j = 0;
  while (true) {
    j = j + this.AllAD[i].Priority;
    if (j >= rndNum) {break;}
    i++;
  }
  this.ShowAD = this.AllAD[i];
}

function CodeZoneAD_Show() {
  if (!this.AllAD) {
    return;
  } else {
    this.GetShowAD();
  }

  if (this.ShowAD == null) return false;
  document.write(this.ShowAD.ADIntro);
}



var ZoneAD_12 = new CodeZoneAD("ZoneAD_12");
ZoneAD_12.ZoneID      = 12;
ZoneAD_12.ZoneWidth   = 0;
ZoneAD_12.ZoneHeight  = 0;
ZoneAD_12.ShowType    = 1;

var objAD = new ObjectAD();
objAD.ADID           = 10;
objAD.ADType         = 4;
objAD.ADName         = "java◊Ó÷’“≥√ÊÕ®¿∏";
objAD.ImgUrl         = "";
objAD.InstallDir     = "http://www.chinaitlab.com/";
objAD.ImgWidth       = 0;
objAD.ImgHeight      = 0;
objAD.FlashWmode     = 0;
objAD.ADIntro        = "<script type=text/javascript>\n\r<!--\n\rsmartad_advid = \"0000000000000000\";\n\rsmartad_adpid = \"2009033017595468\";\n\rsmartad_modeid = \"000001\";\n\rsmartad_width = 960;\n\rsmartad_height = 80;\n\rsmartad_type = \"0\";\n\rsmartad_encoding = \"gb2312\";\n\r//-->\n\r</script>\n\r<script src=\"http://adv.chinaitlab.com/Agent/ShowMe.js\" type=\"text/javascript\"></script>";
objAD.LinkUrl        = "";
objAD.LinkTarget     = 1;
objAD.LinkAlt        = "";
objAD.Priority       = 1;
objAD.CountView      = 1;
objAD.CountClick     = 0;
objAD.ADDIR          = "adjs";
ZoneAD_12.AddAD(objAD);

ZoneAD_12.Show();