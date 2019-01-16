
// ChessBoardDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "ChessBoard.h"
#include "ChessBoardDlg.h"
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
//	afx_msg void OnRButtonDown(UINT nFlags, CPoint point);
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
//	ON_WM_RBUTTONDOWN()
END_MESSAGE_MAP()


// CChessBoardDlg 对话框




CChessBoardDlg::CChessBoardDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CChessBoardDlg::IDD, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CChessBoardDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CChessBoardDlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_WM_LBUTTONDOWN()
	ON_WM_RBUTTONDOWN()
	ON_BN_CLICKED(IDC_WHITE, &CChessBoardDlg::OnBnClickedWhite)
	ON_BN_CLICKED(IDC_BLACK, &CChessBoardDlg::OnBnClickedBlack)
END_MESSAGE_MAP()


// CChessBoardDlg 消息处理程序

BOOL CChessBoardDlg::OnInitDialog()
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
	blackNum = 0;
	whiteNum = 0;
	flag = false;
	for(int i = 0 ; i < 8 ; i++){
		for(int j = 0 ; j < 8 ; j++){
			blank8x8[i][j] = BLANK;
		}
	}
	blank8x8[3][3] = blank8x8[4][4] = WHITE;
	blank8x8[4][3] = blank8x8[3][4] = BLACK;

	return TRUE;  // 除非将焦点设置到控件，否则返回 TRUE
}

void CChessBoardDlg::OnSysCommand(UINT nID, LPARAM lParam)
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

void CChessBoardDlg::OnPaint()
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
		DrawChessBoard();
		DrawChess();
	}
}

//当用户拖动最小化窗口时系统调用此函数取得光标
//显示。
HCURSOR CChessBoardDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}

void CChessBoardDlg::DrawChessBoard(){
	CClientDC dc(this);
	CPen pen(0,1,RGB(0,0,0));
	dc.SelectObject(pen);

	for(int i = 0 ; i < 9 ; i++){
		dc.MoveTo(20,20+30*i);
		dc.LineTo(20+30*8,20+30*i);
		dc.MoveTo(20+30*i,20);
		dc.LineTo(20+30*i,20+30*8);
	}
}

void CChessBoardDlg::DrawChess(){
	CClientDC dc(this);
	for(int i = 0 ; i < 8 ; i++){
		for(int j = 0 ; j < 8 ; j++){
			if(blank8x8[i][j] == BLACK){
				CBrush brushBlack(RGB(0,0,0));
				dc.SelectObject(brushBlack);
				dc.Ellipse(20+30*i,20+30*j,50+30*i,50+30*j);
			}
			else if(blank8x8[i][j] == WHITE){
				CBrush brushWhite(RGB(255,255,255));
				dc.SelectObject(brushWhite);
				dc.Ellipse(20+30*i,20+30*j,50+30*i,50+30*j);
			}
		}
	}
}

bool CChessBoardDlg::CanPlace(int x, int y){
	if(x < 20 || x > 260 || y < 20 || y > 260)
		return false;
	else
		return true;
}

bool CChessBoardDlg::IsOver(int x, int y){
	int count = 1;//记录连子的个数
	//垂直方向
	for(int i = 1 ; y - i >= 0 ; i++){
		if(blank8x8[x][y-i] == currChess)
			count++;
		else
			break;
	}
	for(int i = 1 ; y + i <= 8 ; i++){
		if(blank8x8[x][y+i] == currChess)
			count++;
		else
			break;
	}
	if(count >= 5)
		return true;
	else
		count = 1;

	//水平方向
	for(int i = 1 ; x - i >= 0 ; i++){
		if(blank8x8[x-i][y] == currChess)
			count++;
		else
			break;
	}
	for(int i = 1 ; x + i <= 8 ; i++){
		if(blank8x8[x+i][y] == currChess)
			count++;
		else
			break;
	}
	if(count >= 5)
		return true;
	else
		count = 1;

	//从上到下向左倾斜
	for(int i = 1 ; x - i >= 0 && y - i >= 0; i++){
		if(blank8x8[x-i][y-i] == currChess)
			count++;
		else
			break;
	}
	for(int i = 1 ; y + i <= 8 && x + i <= 8 ; i++){
		if(blank8x8[x+i][y+i] == currChess)
			count++;
		else
			break;
	}
	if(count >= 5)
		return true;
	else
		count = 1;

	//从上到下向右倾斜
	for(int i = 1 ; x + i <= 8 && y - i >= 0; i++){
		if(blank8x8[x+i][y-i] == currChess)
			count++;
		else
			break;
	}
	for(int i = 1 ; y + i <= 8 && x - i >= 0 ; i++){
		if(blank8x8[x+i][y+i] == currChess)
			count++;
		else
			break;
	}
	if(count >= 5)
		return true;
	else
		count = 1;
	return false;
}


void CChessBoardDlg::OnLButtonDown(UINT nFlags, CPoint point)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	int x = (point.x - 20) / 30;
	int y = (point.y - 20) / 30;
	if(flag){
		MessageBox("此局已经结束");
		return;
	}
	if(currChess == WHITE){
		MessageBox("现在应当黑棋下");
		return;
	}
	else{
		currChess = WHITE;
		if(CanPlace(point.x,point.y)){
			blank8x8[x][y] = currChess;
		}
		else{
			MessageBox("请在棋盘内选择");
		}
		this->Invalidate();
		if(IsOver(x,y)){
			MessageBox("白棋胜！");
			flag = true;
		}
	}
	CDialogEx::OnLButtonDown(nFlags, point);
}


void CChessBoardDlg::OnRButtonDown(UINT nFlags, CPoint point)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	int x = (point.x - 20) / 30;
	int y = (point.y - 20) / 30;
	if(flag){
		MessageBox("此局已经结束");
		return;
	}
	if(currChess == BLACK){
		MessageBox("现在应当白棋下");
		return;
	}
	else{
		currChess = BLACK;
		if(CanPlace(point.x,point.y)){
			blank8x8[x][y] = currChess;
		}
		else{
			MessageBox("请在棋盘内选择");
		}
		this->Invalidate();
		if(IsOver(x,y)){
			MessageBox("黑棋胜！");
			flag = true;
		}
	}
	CDialogEx::OnRButtonDown(nFlags, point);
}


void CChessBoardDlg::OnBnClickedWhite()
{
	// TODO: 在此添加控件通知处理程序代码
	currChess = BLACK;
}


void CChessBoardDlg::OnBnClickedBlack()
{
	// TODO: 在此添加控件通知处理程序代码
	currChess = WHITE;
}
