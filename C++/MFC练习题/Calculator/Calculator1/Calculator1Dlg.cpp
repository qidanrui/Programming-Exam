
// Calculator1Dlg.cpp : 实现文件
//

#include "stdafx.h"
#include "Calculator1.h"
#include "Calculator1Dlg.h"
#include "afxdialogex.h"
#include <math.h>
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


// CCalculator1Dlg 对话框




CCalculator1Dlg::CCalculator1Dlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CCalculator1Dlg::IDD, pParent)
	, parameter(0)
	, isPoint(false)
	, sumPoint(0)
	, result(0)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CCalculator1Dlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_DISPLAY, parameter);
}

BEGIN_MESSAGE_MAP(CCalculator1Dlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDC_ONE, &CCalculator1Dlg::OnBnClickedOne)
	ON_BN_CLICKED(IDC_TWO, &CCalculator1Dlg::OnBnClickedTwo)
	ON_BN_CLICKED(IDC_THREE, &CCalculator1Dlg::OnBnClickedThree)
	ON_BN_CLICKED(IDC_FOUR, &CCalculator1Dlg::OnBnClickedFour)
	ON_BN_CLICKED(IDC_FIVE, &CCalculator1Dlg::OnBnClickedFive)
	ON_BN_CLICKED(IDC_SEX, &CCalculator1Dlg::OnBnClickedSex)
	ON_BN_CLICKED(IDC_SEVEN, &CCalculator1Dlg::OnBnClickedSeven)
	ON_BN_CLICKED(IDC_EIGHT, &CCalculator1Dlg::OnBnClickedEight)
	ON_BN_CLICKED(IDC_NINE, &CCalculator1Dlg::OnBnClickedNine)
	ON_BN_CLICKED(IDC_ZERO, &CCalculator1Dlg::OnBnClickedZero)
	ON_BN_CLICKED(IDC_BUTTON5, &CCalculator1Dlg::OnBnClickedButton5)
	ON_BN_CLICKED(IDC_BUTTON9, &CCalculator1Dlg::OnBnClickedButton9)
	ON_BN_CLICKED(IDC_ADD, &CCalculator1Dlg::OnBnClickedAdd)
	ON_BN_CLICKED(IDC_REDUCE, &CCalculator1Dlg::OnBnClickedReduce)
	ON_BN_CLICKED(IDC_MUITIPLE, &CCalculator1Dlg::OnBnClickedMuitiple)
	ON_BN_CLICKED(IDC_DIVISION, &CCalculator1Dlg::OnBnClickedDivision)
	ON_BN_CLICKED(IDC_CALCULATE, &CCalculator1Dlg::OnBnClickedCalculate)
	ON_BN_CLICKED(IDC_CLEAR, &CCalculator1Dlg::OnBnClickedClear)
END_MESSAGE_MAP()


// CCalculator1Dlg 消息处理程序

BOOL CCalculator1Dlg::OnInitDialog()
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

	// 设置此对话框的图标。当应用程序主窗口不是对话框时，框架将自动
	//  执行此操作
	SetIcon(m_hIcon, TRUE);			// 设置大图标
	SetIcon(m_hIcon, FALSE);		// 设置小图标

	// TODO: 在此添加额外的初始化代码

	return TRUE;  // 除非将焦点设置到控件，否则返回 TRUE
}

void CCalculator1Dlg::OnSysCommand(UINT nID, LPARAM lParam)
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

void CCalculator1Dlg::OnPaint()
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
HCURSOR CCalculator1Dlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}



void CCalculator1Dlg::OnBnClickedOne()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(!isPoint){
		parameter = parameter * 10 + 1;
	}
	else{
		parameter = parameter + 1 / pow(10.0,sumPoint);
		sumPoint++;
	}
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedTwo()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(!isPoint){
		parameter = parameter * 10 + 2;
	}
	else{
		parameter = parameter + 2 / pow(10.0,sumPoint);
		sumPoint++;
	}
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedThree()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(!isPoint){
		parameter = parameter * 10 + 3;
	}
	else{
		parameter = parameter + 3 / pow(10.0,sumPoint);
		sumPoint++;
	}
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedFour()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(!isPoint){
		parameter = parameter * 10 + 4;
	}
	else{
		parameter = parameter + 4 / pow(10.0,sumPoint);
		sumPoint++;
	}
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedFive()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(!isPoint){
		parameter = parameter * 10 + 5;
	}
	else{
		parameter = parameter + 5 / pow(10.0,sumPoint);
		sumPoint++;
	}
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedSex()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(!isPoint){
		parameter = parameter * 10 + 6;
	}
	else{
		parameter = parameter + 6 / pow(10.0,sumPoint);
		sumPoint++;
	}
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedSeven()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(!isPoint){
		parameter = parameter * 10 + 7;
	}
	else{
		parameter = parameter + 7 / pow(10.0,sumPoint);
		sumPoint++;
	}
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedEight()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(!isPoint){
		parameter = parameter * 10 + 8;
	}
	else{
		parameter = parameter + 8 / pow(10.0,sumPoint);
		sumPoint++;
	}
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedNine()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(!isPoint){
		parameter = parameter * 10 + 9;
	}
	else{
		parameter = parameter + 9 / pow(10.0,sumPoint);
		sumPoint++;
	}
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedZero()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(!isPoint){
		parameter = parameter * 10 + 0;
	}
	else{
		parameter = parameter + 0 / pow(10.0,sumPoint);
		sumPoint++;
	}
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedButton5()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	isPoint = true;
	sumPoint = 1;
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedButton9()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	parameter = -parameter;
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedAdd()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	result = parameter;
	parameter = 0;
	isPoint = false;
	sumPoint = 0;
	opera = '+';
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedReduce()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	result = parameter;
	parameter = 0;
	isPoint = false;
	sumPoint = 0;
	opera = '-';
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedMuitiple()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	result = parameter;
	parameter = 0;
	isPoint = false;
	sumPoint = 0;
	opera = 'x';
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedDivision()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	result = parameter;
	parameter = 0;
	isPoint = false;
	sumPoint = 0;
	opera = '/';
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedCalculate()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	switch(opera){
	case '+':
		result += parameter;
		parameter = result;
		break;
	case '-':
		result -= parameter;
		parameter = result;
		break;
	case 'x':
		result *= parameter;
		parameter = result;
		break;
	case '/':
		if(parameter){
			result /= parameter;
			parameter = result;
		}
		else{
			parameter = 0;
			MessageBox("除数不能为0");
		}
		break;
	}
	UpdateData(false);
}


void CCalculator1Dlg::OnBnClickedClear()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	parameter = 0;
	result = 0;
	isPoint = false;
	sumPoint = 0;
	UpdateData(false);
}
