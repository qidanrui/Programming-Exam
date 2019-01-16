
// ScoreDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "Score.h"
#include "ScoreDlg.h"
#include "afxdialogex.h"
#include "conio.h"
#include "MyDialog.h"
#include <afxdb.h>
#include <odbcinst.h>
#include <afxdlgs.h>

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CScoreDlg 对话框



CScoreDlg::CScoreDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CScoreDlg::IDD, pParent)
	, Number(_T(""))
	, Name(_T(""))
	, Score(_T(""))
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CScoreDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_LIST2, TotalList);
	DDX_Text(pDX, IDC_EDIT1, Number);
	DDX_Text(pDX, IDC_EDIT2, Name);
	DDX_Text(pDX, IDC_EDIT3, Score);
	DDX_Control(pDX, IDC_BUTTON1, In);
	DDX_Control(pDX, IDC_BUTTON2, Out);
	DDX_Control(pDX, IDC_BUTTON3, Add);
	DDX_Control(pDX, IDC_BUTTON4, Delete);
	DDX_Control(pDX, IDC_BUTTON5, Exit);
}

BEGIN_MESSAGE_MAP(CScoreDlg, CDialogEx)
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDC_BUTTON1, &CScoreDlg::OnBnClickedButton1)
	ON_EN_CHANGE(IDC_EDIT2, &CScoreDlg::OnEnChangeEdit2)
	ON_BN_CLICKED(IDC_BUTTON5, &CScoreDlg::OnBnClickedButton5)
	ON_BN_CLICKED(IDC_BUTTON2, &CScoreDlg::OnBnClickedButton2)
	ON_BN_CLICKED(IDC_BUTTON3, &CScoreDlg::OnBnClickedButton3)
	ON_BN_CLICKED(IDC_BUTTON4, &CScoreDlg::OnBnClickedButton4)
	ON_NOTIFY(NM_CLICK, IDC_LIST2, &CScoreDlg::OnNMClickList2)
END_MESSAGE_MAP()


// CScoreDlg 消息处理程序

BOOL CScoreDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// 设置此对话框的图标。当应用程序主窗口不是对话框时，框架将自动
	//  执行此操作
	SetIcon(m_hIcon, TRUE);			// 设置大图标
	SetIcon(m_hIcon, FALSE);		// 设置小图标

	// TODO: 在此添加额外的初始化代码
	AllocConsole();
	DWORD dwStyle = TotalList.GetExtendedStyle();
	dwStyle |= LVS_EX_FULLROWSELECT;//选中某行使整行高亮（只适用与report风格的listctrl）
	TotalList.SetExtendedStyle(dwStyle); //设置扩展风格
	TotalList.DeleteAllItems();//清空
	TotalList.InsertColumn(0,_T("序号"),LVCFMT_LEFT,150,0);//添加列
	TotalList.InsertColumn(1,_T("学号"),LVCFMT_LEFT,150,1);
	TotalList.InsertColumn(2,_T("姓名"),LVCFMT_LEFT,150,2);
	TotalList.InsertColumn(3,_T("成绩"),LVCFMT_LEFT,150,3);
	//TotalList.InsertItem(0,_T("Item 1_1"));//插入行
	//TotalList.SetItemText(0,1,_T("item"));
	TotalList.SetRedraw(TRUE);
	return TRUE;  // 除非将焦点设置到控件，否则返回 TRUE
}

// 如果向对话框添加最小化按钮，则需要下面的代码
//  来绘制该图标。对于使用文档/视图模型的 MFC 应用程序，
//  这将由框架自动完成。

void CScoreDlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // 用于绘制的设备上下文

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// 使图标在工作区矩形中居中
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// 绘制图标
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
	}
}

//当用户拖动最小化窗口时系统调用此函数取得光标
//显示。
HCURSOR CScoreDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}



void CScoreDlg::OnBnClickedButton1()
{
	// TODO: 在此添加控件通知处理程序代码
	CFileDialog file(true,NULL,NULL,OFN_OVERWRITEPROMPT|OFN_HIDEREADONLY|OFN_ALLOWMULTISELECT,_T("Worksheet Files(*.xls,*.xlsx)|*.xls,*.xlsx|TXT Files(*.txt)|*.txt|All Files(*.*)|*.*||"),this);
	file.m_ofn.lpstrTitle = _T("请选择相关文件");
	file.m_ofn.nMaxFile = 100;
	file.m_ofn.lpstrFile = new TCHAR[file.m_ofn.nMaxFile];
	ZeroMemory(file.m_ofn.lpstrFile,sizeof(TCHAR)*file.m_ofn.nMaxFile);
	int readFile = file.DoModal();
	if (readFile == IDCANCEL)
		return;
	POSITION filePosition;
	filePosition = file.GetStartPosition();
	CArray<CString,CString>fileName;
	while (filePosition != NULL)
		fileName.Add(file.GetNextPathName(filePosition));
	for (int i = 0; i < fileName.GetSize(); i++)
	{
		CString str;
		str = fileName.GetAt(i).Right(3);
		if ((str == "XLS")||(str == "XLSX")||(str == "xls")||(str == "xlsx"))
		{
			//readAndShowExcelFile(fileName.GetAt(i));
		}
		else if ((str == "TXT")||(str == "txt"))
		{
			readAndShowTxtFile(fileName.GetAt(i));
		}
	}
}


void CScoreDlg::OnEnChangeEdit2()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CDialogEx::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。


	// TODO:  在此添加控件通知处理程序代码
}


void CScoreDlg::OnBnClickedButton5()
{
	// TODO: 在此添加控件通知处理程序代码
	exit(0);
}

void CScoreDlg::readAndShowTxtFile(CString fileName)
{
	CStdioFile myFile;
	CString str;
	CString LineNumber;
	CString data[3];
	int i;
	int n;
	int linenumber;
	CRect rect;
	if(myFile.Open(fileName, CFile::modeRead))
	{
		while(myFile.ReadString(str))
		{
			n = str.GetLength();
			linenumber = TotalList.GetItemCount();
			for (i = 0; i < 2; i++)
			{
				int mark = str.Find(' ');
				data[i] = str.Mid(0,mark);
				str = str.Mid(mark + 1,n - mark - 1);
			}
			data[2] = str;
			if (!judge(data[0])) continue;
			LineNumber.Format(_T("%d"),linenumber + 1);
			TotalList.InsertItem(linenumber,LineNumber);
			for (i = 0; i < 3; i++)
			{
				TotalList.SetItemText(linenumber,i + 1,data[i]);
			}
		}
		myFile.Close();
	}
}

void CScoreDlg::OnBnClickedButton2()
{
	CFileDialog file(false,NULL,NULL,OFN_FILEMUSTEXIST|OFN_PATHMUSTEXIST|OFN_READONLY,_T("Worksheet Files(*.xls,*.xlsx)|*.xls,*.xlsx|TXT Files(*.txt)|*.txt|All Files(*.*)|*.*||"),this);
	file.DoModal();
	CString fileName;
	fileName = file.GetPathName();
	CString str;
	str = fileName.Right(3);
	if ((str == "XLS")||(str == "XLSX")||(str == "xls")||(str == "xlsx"))
	{
		writeExcelFile(fileName);
	}
	else if ((str == "TXT")||(str == "txt"))
	{
		writeTxtFile(fileName);
	}
}


void CScoreDlg::OnBnClickedButton3()
{
	// TODO: 在此添加控件通知处理程序代码
	CMyDialog Dlg;
	int linenumber = 0;
	CString LineNumber;
	Dlg.DoModal();
	linenumber = TotalList.GetItemCount();
	LineNumber.Format(_T("%d"),linenumber + 1);
	if (judge(Dlg.AddNumber))
	{
		TotalList.InsertItem(linenumber,LineNumber);
		TotalList.SetItemText(linenumber,1,Dlg.AddNumber);
		TotalList.SetItemText(linenumber,2,Dlg.AddName);
		TotalList.SetItemText(linenumber,3,Dlg.AddScore);
	}
	else
	{
		AfxMessageBox("此学生已存在!");
		return;
	}
}


void CScoreDlg::OnBnClickedButton4()
{
	// TODO: 在此添加控件通知处理程序代码
	int itemPosition[100];
	int all = -1;
	POSITION pos = TotalList.GetFirstSelectedItemPosition();
	if (pos == NULL)
		return;
	else
	{
		while (pos)
		{
			all++;
			int nItem = TotalList.GetNextSelectedItem(pos);
			itemPosition[all] = nItem;
		}
	}
	for (int i = all; i >= 0; i--)
	{
		TotalList.DeleteItem(itemPosition[i]);
	}

	//给行重新编码
	int count;
	int i;
	CString count1;
	count = TotalList.GetItemCount();
	for (i = 0; i < count; i++)
	{
		count1.Format(_T("%d"),i+1);
		TotalList.SetItemText(i,0,count1);
	}
}


void CScoreDlg::OnNMClickList2(NMHDR *pNMHDR, LRESULT *pResult)
{
	LPNMITEMACTIVATE pNMItemActivate = reinterpret_cast<LPNMITEMACTIVATE>(pNMHDR);
	*pResult = 0;
	// TODO: 在此添加控件通知处理程序代码
	POSITION pos = TotalList.GetFirstSelectedItemPosition();
	if (pos == NULL)
		return;
	else
	{
		int nItem = TotalList.GetNextSelectedItem(pos);
		Number = TotalList.GetItemText(nItem,1);
		Name = TotalList.GetItemText(nItem,2);
		Score = TotalList.GetItemText(nItem,3);
	}
	UpdateData(FALSE);
	*pResult = 0;
}

void CScoreDlg::writeTxtFile(CString FileName)
{
	//导出过程
	CStdioFile myWriteFile;
	CString str;
	int linenumber;
	myWriteFile.Open(FileName,CFile::modeCreate|CFile::modeWrite);
	UpdateData(TRUE);
	int i;
	linenumber = TotalList.GetItemCount();
	_cprintf("%d",linenumber);
	for (i = 0; i < linenumber; i++)
	{
		//str = TotalList.GetItemText(i,0);
		//str += ' ';
		str = "";
		str +=TotalList.GetItemText(i,1);
		str += ' ';
		str +=TotalList.GetItemText(i,2);
		str += ' ';
		str +=TotalList.GetItemText(i,3);
		str += ' ';
		str += "\n";
		myWriteFile.WriteString(str);
	}
	MessageBox(_T("导出成功！"),_T("提示信息"),MB_OK);
}

/*void CScoreDlg::readAndShowExcelFile(CString FileName)
{
	//ShellExecute(NULL,NULL,FileName,NULL,NULL,SW_RESTORE);
	CDatabase db;
	CString sDriver = _T("MICROSOFT EXCEL DRIVER(*.XLS)");
	CString sSql,arr[3];
	//CString ExcelDriver = GetExcelDriver();
	sSql.Format(_T("DRIVER={%s};DSN='';FIRSTROWHASNAMES=1;READONLY=FALSE;CREATE_DB=\"%s\";DBQ=%s",sDriver, FileName, FileName));
	if (!db.OpenEx(sSql,CDatabase::noOdbcDialog))
	{
		MessageBox(_T("打开Excel文件失败！"),_T("错误"),MB_OK);
		return;
	}
	//CRecordset pset(&db);
	//AfxMessageBox(_T("OK"));
	//sSql.Format(_T("SELECT 学号,姓名,成绩 FROM 1"));
	//pset.Open(CRecordset::forwardOnly,sSql,CRecordset::readOnly);
	//while(!pset.IsEOF())
	//{
		//pset.GetFieldValue(_T("学号"),arr[0]);//前面字段必须与表中的相同，否则出错。
		//pset.GetFieldValue(_T("姓名"),arr[1]);
		//pset.GetFieldValue(_T("成绩"),arr[2]);
		
	//	int count = TotalList.GetItemCount();//插入到ListCtrl中
	//	TotalList.InsertItem(count,arr[0]);
		//TotalList.SetItemText(count,1,arr[1]);
	//	TotalList.SetItemText(count,2,arr[2]);
	//	pset.MoveNext();
	//}
 db.Close();
 MessageBox(_T("Excel数据成功导入系统!"),_T("导入成功"));
}*/

CString CScoreDlg::GetExcelDriver()
{
	char szBuf[2001];
    WORD cbBufMax = 2000;
    WORD cbBufOut;
    char *pszBuf = szBuf;
    CString sDriver;
    // 获取已安装驱动的名称(涵数在odbcinst.h里)
    if (!SQLGetInstalledDrivers(szBuf, cbBufMax, &cbBufOut))
        return ("");
   
    // 检索已安装的驱动是否有Excel...
    do
    {
        if (strstr(pszBuf, "Excel") != 0)
        {
            //发现 !
            sDriver = CString(pszBuf);
            break;
        }
        pszBuf = strchr(pszBuf, '\0') + 1;
    }
    while (pszBuf[1] != '\0');
    return sDriver;
}

void CScoreDlg::writeExcelFile(CString FileName)
{
	if ( TotalList.GetItemCount() <= 0 )
	{
		AfxMessageBox("列表中没有记录需要保存！");
		return;
	}
	
	DWORD dwRe = GetFileAttributes(FileName);
	if ( dwRe != (DWORD)-1 )
	{
		DeleteFile(FileName);
	}
	
	CDatabase database;
	CString sDriver = "MICROSOFT EXCEL DRIVER (*.XLS)"; 
	CString sSql,strInsert;
	
	TRY
	{
		// 创建进行存取的字符串
		sSql.Format("DRIVER={%s};DSN='';FIRSTROWHASNAMES=1;READONLY=FALSE;CREATE_DB=\"%s\";DBQ=%s",sDriver, FileName, FileName);

       // 创建数据库 (既Excel表格文件)
		if( database.OpenEx(sSql,CDatabase::noOdbcDialog))
		{
			//获得列别框总列数
			int iColumnNum,iRowCount;
			LVCOLUMN lvCol;
			CString strColName; //用于保存列标题名称
			int i,j; //列、行循环参数
			
			iColumnNum = TotalList.GetHeaderCtrl()->GetItemCount();
			iRowCount = TotalList.GetItemCount();

			sSql = " CREATE TABLE DSO_DX ( ";
			strInsert = " INSERT INTO DSO_DX ( " ;
			//获得列标题名称
			lvCol.mask = LVCF_TEXT; //必需设置，说明LVCOLUMN变量中pszText参数有效
			lvCol.cchTextMax = 32; //必设，pszText参数所指向的字符串的大小
			lvCol.pszText = strColName.GetBuffer(32); //必设，pszText 所指向的字符串的实际存储位置。
			//以上三个参数设置后才能通过 GetColumn()函数获得列标题的名称
			for( i=0 ; i< iColumnNum ; i++ )
			{
				if ( !(TotalList.GetColumn(i,&lvCol)))
					return;
				if ( i<iColumnNum-1 )
				{
					sSql = sSql + lvCol.pszText + " TEXT , ";
					strInsert = strInsert + lvCol.pszText + " , ";
				}
				else
				{
					sSql = sSql + lvCol.pszText + " TEXT ) ";
					strInsert = strInsert + lvCol.pszText + " )  VALUES ( ";
				}
			}
			//创建Excel表格文件
			database.ExecuteSQL(sSql);
			
			//循环提取记录并插入到EXCEL中
			sSql = strInsert;
			char chTemp[33];
			for ( j=0 ; j<iRowCount ; j++ )
			{
				memset(chTemp,0,33);
				for ( i=0 ; i<iColumnNum ; i++ )
				{
					TotalList.GetItemText(j,i,chTemp,33);
					if ( i < (iColumnNum-1) )
					{
						sSql = sSql + "'" + chTemp + "' , ";
					}
					else
					{
						sSql = sSql + "'" + chTemp + "' ) ";
					}
				}
				//将记录插入到表格中
				database.ExecuteSQL(sSql);
				sSql = strInsert; 
			}
		}     
	
		// 关闭Excel表格文件
		database.Close();
		
		AfxMessageBox("保存查询结果为Excel文件成功！");
	}
	CATCH_ALL(e)
	{
		//错误类型很多，根据需要进行报错。
		AfxMessageBox("Excel文件保存失败。");
	}
	END_CATCH_ALL;
}

bool CScoreDlg::judge(CString studentNumber)
{
	CString number;
	int n;
	int i;
	n = TotalList.GetItemCount();
	for (i = 0; i < n; i++)
	{
		number = TotalList.GetItemText(i,1);
		if (studentNumber == number)
			return false;
	}
	return true;
}