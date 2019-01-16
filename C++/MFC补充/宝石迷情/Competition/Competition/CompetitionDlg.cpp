
// CompetitionDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "Competition.h"
#include "CompetitionDlg.h"
#include "afxdialogex.h"
#include "conio.h"
#include <string>
using std::string;
#include <strstream>

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// 用于应用程序“关于”菜单项的 CAboutDlg 对话框

class CAboutDlg : public CDialogEx
{
public:
	CAboutDlg();
// 对话框数据
	enum { IDD = IDD_ABOUTBOX };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

// 实现
protected:
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialogEx(CAboutDlg::IDD)
{
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialogEx)
END_MESSAGE_MAP()


// CCompetitionDlg 对话框



CCompetitionDlg::CCompetitionDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CCompetitionDlg::IDD, pParent)
	, Blue(_T(""))
	, Red(_T(""))
	, Who(_T(""))
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CCompetitionDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, Blue);
	DDX_Text(pDX, IDC_EDIT2, Red);
	DDX_Text(pDX, IDC_EDIT3, Who);
	DDX_Control(pDX, IDC_BUTTON1, Start);
	DDX_Control(pDX, IDC_BUTTON2, Exit);
}

BEGIN_MESSAGE_MAP(CCompetitionDlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	//ON_LBN_SELCHANGE(IDC_LIST1, &CCompetitionDlg::OnLbnSelchangeList1)
	ON_EN_CHANGE(IDC_EDIT2, &CCompetitionDlg::OnEnChangeEdit2)
	ON_BN_CLICKED(IDC_BUTTON1, &CCompetitionDlg::OnBnClickedButton1)
	ON_WM_LBUTTONDOWN()
	ON_EN_CHANGE(IDC_EDIT3, &CCompetitionDlg::OnEnChangeEdit3)
	ON_BN_CLICKED(IDC_BUTTON2, &CCompetitionDlg::OnBnClickedButton2)
END_MESSAGE_MAP()


// CCompetitionDlg 消息处理程序

BOOL CCompetitionDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// 将“关于...”菜单项添加到系统菜单中。

	// IDM_ABOUTBOX 必须在系统命令范围内。
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != NULL)
	{
		BOOL bNameValid;
		CString strAboutMenu;
		bNameValid = strAboutMenu.LoadString(IDS_ABOUTBOX);
		ASSERT(bNameValid);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}
	//::SetWindowPos(this->m_hWnd,HWND_BOTTOM,0,0,1024,768,SWP_NOZORDER);
	CenterWindow();
	AllocConsole();
	// 设置此对话框的图标。当应用程序主窗口不是对话框时，框架将自动
	//  执行此操作
	SetIcon(m_hIcon, TRUE);			// 设置大图标
	SetIcon(m_hIcon, FALSE);		// 设置小图标
	// TODO: 在此添加额外的初始化代码
    //**********************************//
	int i;
	int j;
	RedScore = 0;
	BlueScore = 0;
	blueOrRed = true;
	Begin = false;
	for (i = 0; i < 8; i++)
		for (j = 0; j < 8; j++)
			judge[i][j] = 0;
	//**********************************//
	
	return TRUE;  // 除非将焦点设置到控件，否则返回 TRUE
}

void CCompetitionDlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialogEx::OnSysCommand(nID, lParam);
	}
}

// 如果向对话框添加最小化按钮，则需要下面的代码
//  来绘制该图标。对于使用文档/视图模型的 MFC 应用程序，
//  这将由框架自动完成。

void CCompetitionDlg::OnPaint()
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
		int wwidth[9] = {30,100,170,240,310,380,450,520,590};
		int hheight[9] = {130,200,270,340,410,480,550,620,690};
		CPaintDC   dc(this); 
		CRect rect;
		int i;
		int j;
		GetClientRect(&rect);
		text();
		for (i = 0; i <= 8; i++)
		{
			dc.MoveTo(30 + i * 70,130); 
			dc.LineTo(30 + i * 70,690);
		}
		dc.MoveTo(30,140);
		for (i = 0; i <= 8;i++)
		{
			dc.MoveTo(30,130 + i * 70);
			dc.LineTo(590,130 + i * 70);
		}
		/*for (i = 0; i <8; i++)
		{
			for (j = 0;j <8;j++)
				_cprintf("%d",judge[i][j]);
			_cprintf("\n");
		}*/
		for (i = 0; i < 8; i++)
			for (j = 0; j < 8; j++)
			{
				if (judge[j][i] == 2)
				{
					CBrush brush(RGB(255,0,0));
					dc.SelectObject(&brush);
					dc.Rectangle(wwidth[i],hheight[j],wwidth[i+1],hheight[j+1]);
					DeleteObject(brush);
				}
				if (judge[j][i] == 1)
				{
					CBrush brush(RGB(0,0,255));
					dc.SelectObject(&brush);
					dc.Rectangle(wwidth[i],hheight[j],wwidth[i+1],hheight[j+1]);
					DeleteObject(brush);
				}
			}
		CDialogEx::OnPaint();
	}
}

//当用户拖动最小化窗口时系统调用此函数取得光标
//显示。
HCURSOR CCompetitionDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}



void CCompetitionDlg::OnLbnSelchangeList1()
{
	// TODO: 在此添加控件通知处理程序代码
}


void CCompetitionDlg::OnEnChangeEdit2()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CDialogEx::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码
}


void CCompetitionDlg::OnBnClickedButton1()
{
	Begin = true;
	// TODO: 在此添加控件通知处理程序代码
}


void CCompetitionDlg::OnLButtonDown(UINT nFlags, CPoint point)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	CDialogEx::OnLButtonDown(nFlags, point);
	CClientDC dc(this);
	int x;
	int y;
	int i;
	int j;
	int wwidth[9] = {30,100,170,240,310,380,450,520,590};
	int hheight[9] = {130,200,270,340,410,480,550,620,690};
	if (Begin)
	{
		for (i = 0; i < 8; i++)
			for (j  = 0; j < 8; j++)
				numberRed[i][j] = 0;
		for (i = 0; i < 8; i++)
			for (j  = 0; j < 8; j++)
				numberBlue[i][j] = 0;
		x = point.x;
		y = point.y;
		for (i = 0; i <8 ; i++)
		{
			if ((x>=wwidth[i]) && (x < wwidth[i +1]))
			{
				for (j = 0; j < 8; j ++)
				{
					if ((y >=hheight[j]) && (y < hheight[j + 1]) && (judge[j][i] == 0))
					{
						if (blueOrRed)
						{
							judge[j][i] = 1;
							blueOrRed = !blueOrRed;
							Invalidate(TRUE);
						}
						else 
						{
							judge[j][i] = 2;
							blueOrRed = !blueOrRed;
							Invalidate(TRUE);
						}
						break;
					}
				}
			}
		}//画出各种的方块嗯嗯>_<
		//*************//
		//以行为单位寻找3个以上的
		for (i = 0; i < 8; i++)
			for (j = 1; j <7; j++)
			{
				if ((judge[j][i] == judge[j - 1][i]) && (judge[j][i] == judge[j+1][i]))
				{
					if (judge[j][i] == 1)
					{
						numberBlue[j][i] = 1;
						numberBlue[j - 1][i] = 1;
						numberBlue[j+1][i] = 1;
					}
					if (judge[j][i] == 2)
					{
						numberRed[j][i] = 1;
						numberRed[j - 1][i] = 1;
						numberRed[j+1][i] = 1;
					}
				}
			}
		//以列为单位寻找3个以上的
		for (i = 0; i < 8; i++)
			for (j = 1; j <7; j++)
			{
				if ((judge[i][j] == judge[i][j - 1]) && (judge[i][j] == judge[i][j+1]))
				{
					if (judge[i][j] == 1)
					{
						numberBlue[i][j] = 1;
						numberBlue[i][j-1] = 1;
						numberBlue[i][j+1] = 1;
					}
					if (judge[i][j] == 2)
					{
						numberRed[i][j] = 1;
						numberRed[i][j-1] = 1;
						numberRed[i][j+1] = 1;
					}
				}
			}
		for (i = 1; i < 7; i++)
			for (j = 1; j < 7; j++)
			{
				if ((numberBlue[i][j] == numberBlue[i-1][j]) && (numberBlue[i][j] == numberBlue[i][j-1]) 
					&& (numberBlue[i][j] == numberBlue[i+1][j]) && (numberBlue[i][j] == numberBlue[i][j+1])
					&& (numberBlue[i][j] > 0))
					numberBlue[i][j]++;
				if ((numberRed[i][j] == numberRed[i-1][j]) && (numberRed[i][j] == numberRed[i][j-1]) 
					&& (numberRed[i][j] == numberRed[i+1][j]) && (numberRed[i][j] == numberRed[i][j+1])
					&& (numberRed[i][j] > 0))
					numberRed[i][j]++;
			}
			for (i = 0; i < 8; i++)
			{
				for (j = 0; j < 8; j++)
					_cprintf("%d",numberBlue[i][j]);
				_cprintf("\n");
			}
		for (i = 0; i < 8; i++)
			for (j = 0; j < 8; j++)
			{
				if ((numberBlue[i][j] > 0) || (numberRed[i][j] > 0))
					judge[i][j] = 0;
			}
		for (i = 0; i < 8; i++)
			for (j = 0; j < 8; j++)
			{
				RedScore  += numberRed[i][j];
				//_cprin
				BlueScore += numberBlue[i][j];
			}
			if (Gameover())
			{
				if (RedScore > BlueScore)
					MessageBox(TEXT("游戏结束，红方胜!"),TEXT("游戏结束"),MB_OK);
				else
					if (BlueScore > RedScore)
						MessageBox(TEXT("游戏结束，蓝方胜!"),TEXT("游戏结束"),MB_OK);
					else
						MessageBox(TEXT("游戏结束，平局!"),TEXT("游戏结束"),MB_OK);
			}
			Invalidate(TRUE);
		
}
}

void CCompetitionDlg::OnEnChangeEdit3()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CDialogEx::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。
	if (blueOrRed)
		SetDlgItemText(IDC_EDIT3,TEXT("请蓝方放置宝石"));
	else SetDlgItemText(IDC_EDIT3,TEXT("请红方放置宝石"));

	// TODO:  在此添加控件通知处理程序代码
}


void CCompetitionDlg::OnBnClickedButton2()
{
	exit(0);
	// TODO: 在此添加控件通知处理程序代码
}

void CCompetitionDlg::text()
{
	if (blueOrRed)
		Who = _T("请蓝方放置宝石");
	else Who = _T("请红方放置宝石");
	CString s;
	s.Format(_T("蓝方得分：%d"),BlueScore);
	Blue = s;
	s.Format(_T("红方得分：%d"),RedScore);
	Red =s;
	UpdateData(false);
}

bool CCompetitionDlg::Gameover()
{
	int i;
	int j;
	for (i = 0; i < 8; i++)
		for (j = 0; j < 8; j++)
		{
			if (judge[i][j] == 0)
				return false;
		}
	return true;
}

