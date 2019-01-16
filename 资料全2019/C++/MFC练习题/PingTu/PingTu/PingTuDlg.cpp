
// PingTuDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "PingTu.h"
#include "PingTuDlg.h"
#include "afxdialogex.h"

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
public:
//	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
};

CAboutDlg::CAboutDlg() : CDialogEx(CAboutDlg::IDD)
{
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialogEx)
//	ON_WM_LBUTTONDOWN()
END_MESSAGE_MAP()


// CPingTuDlg 对话框




CPingTuDlg::CPingTuDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CPingTuDlg::IDD, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CPingTuDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CPingTuDlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_WM_LBUTTONDOWN()
END_MESSAGE_MAP()


// CPingTuDlg 消息处理程序

BOOL CPingTuDlg::OnInitDialog()
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
	align = 50;
	width = 50;
	initBoard();

	return TRUE;  // 除非将焦点设置到控件，否则返回 TRUE
}

void CPingTuDlg::OnSysCommand(UINT nID, LPARAM lParam)
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

void CPingTuDlg::OnPaint()
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
		drawChessBoard();
		drawChess();
		if(isOver())
			MessageBox("you win");
	}
}

//当用户拖动最小化窗口时系统调用此函数取得光标
//显示。
HCURSOR CPingTuDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}

void CPingTuDlg::drawChessBoard(){
	CClientDC dc(this);
	CPen pen(PS_SOLID,1,RGB(0,0,0));
	dc.SelectObject(dc);
	for(int i = 0 ; i < 5 ; i++){
		dc.MoveTo(align,align+width*i);
		dc.LineTo(align+width*4,align+width*i);
		dc.MoveTo(align+width*i,align);
		dc.LineTo(align+width*i,align+width*4);
	}
}
void CPingTuDlg::drawChess(){
	CClientDC dc(this);
	CPen pen(PS_SOLID,2,RGB(0,0,0));
	dc.SelectObject(pen);
	for(int i = 0 ; i < 4 ; i++){
		for(int j = 0 ; j < 4 ; j++){
			if(chesses[j][i] != -1){
				CString text;
				text.Format("%d",chesses[j][i]);
				RECT rect;
				rect.left = align + width * j;
				rect.top = align + width * i  + width / 3;
				rect.right = align + width * (j + 1);
				rect.bottom = align + width * (i + 1);
				dc.DrawText(text,&rect,DT_CENTER);
			}
			else{
				empty_x = j;
				empty_y = i;
			}
		}
	}
}
void CPingTuDlg::initBoard(){
	int count = 1;
	for(int i = 0; i < 4 ; i++){
		for(int j = 0 ; j < 4 ; j++){
			chesses[j][i] = -1;
		}
	}

	srand(time(0));
	while(count < 16){
		int x = rand()%4;
		int y = rand()%4;
		
		if(chesses[x][y] == -1){
			chesses[x][y] = count;
			count++;
		}
	}
	
}

bool CPingTuDlg::ifChangeLeft(int x,int y){
	if(x==empty_x-1 && y==empty_y)
		return true;
	else
		return false;
}
bool CPingTuDlg::ifChangeRight(int x,int y){
	if(x==empty_x+1 && y==empty_y)
		return true;
	else
		return false;
}
bool CPingTuDlg::ifChangeUp(int x,int y){
	if(x==empty_x && y==empty_y-1)
		return true;
	else
		return false;
}
bool CPingTuDlg::ifChangeDown(int x,int y){
	if(x==empty_x && y==empty_y+1)
		return true;
	else
		return false;
}
void CPingTuDlg::change(int x,int y){
	chesses[empty_x][empty_y] = chesses[x][y];
	chesses[x][y] = -1;
}

void CPingTuDlg::OnLButtonDown(UINT nFlags, CPoint point)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	int x = (point.x-align)/width;
	int y = (point.y-align)/width;
	if(ifChangeUp(x,y))
		change(x,y);
	if(ifChangeLeft(x,y))
		change(x,y);
	if(ifChangeRight(x,y))
		change(x,y);
	if(ifChangeDown(x,y))
		change(x,y);
	Invalidate();

	CDialogEx::OnLButtonDown(nFlags, point);
}

bool CPingTuDlg::isOver(){
	for(int i = 0 ; i < 4 ; i++){
		for(int j = 0 ; j < 4 ; j++){
			if(i == 3 && j == 3){
				if(chesses[j][i] != -1)
					return false;
			}
			else{
				if(chesses[j][i] != i*4+j){
					return false;
				}
			}
			
		}
	}
	return true;
}