
// OthelloDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "Othello.h"
#include "OthelloDlg.h"
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


// COthelloDlg 对话框




COthelloDlg::COthelloDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(COthelloDlg::IDD, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void COthelloDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(COthelloDlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_WM_LBUTTONDOWN()
END_MESSAGE_MAP()


// COthelloDlg 消息处理程序

BOOL COthelloDlg::OnInitDialog()
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

	for(int i = 0 ; i < 8 ; i++){
		for(int j = 0 ; j < 8 ; j++){
			chesses[i][j] = BLANK;
		}
	}
	white = 0;
	black = 0;
	align = 20;
	width = 30;
	curChess = BLACK;
	chesses[3][3] = chesses[4][4] = BLACK;
	chesses[3][4] = chesses[4][3] = WHITE;

	return TRUE;  // 除非将焦点设置到控件，否则返回 TRUE
}

void COthelloDlg::OnSysCommand(UINT nID, LPARAM lParam)
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

void COthelloDlg::OnPaint()
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
			tellFinal();
	}
}

//当用户拖动最小化窗口时系统调用此函数取得光标
//显示。
HCURSOR COthelloDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}

void COthelloDlg::drawChessBoard(){
	CClientDC dc(this);
	CPen pen(PS_SOLID,1,RGB(0,0,0));
	dc.SelectObject(pen);
	
	for(int i = 0 ; i < 9 ; i++){
		dc.MoveTo(align,align+width*i);
		dc.LineTo(align+width*8,align+width*i);
		dc.MoveTo(align+width*i,align);
		dc.LineTo(align+width*i,align+width*8);
	}
}

void COthelloDlg::drawChess(){
	CClientDC dc(this);
	CBrush black_brush(RGB(0,0,0));
	CBrush white_brush(RGB(255,255,255));
	for(int i = 0 ; i < 8 ; i++){
		for(int j = 0 ; j < 8 ; j++){
			if(chesses[j][i] == BLACK){
				dc.SelectObject(black_brush);
				dc.Ellipse(align+width*j,align+width*i,align+width*(j+1),align+width*(i+1));
			}
			else if(chesses[j][i] == WHITE){
				dc.SelectObject(white_brush);
				dc.Ellipse(align+width*j,align+width*i,align+width*(j+1),align+width*(i+1));
			}
		}
	}
}

bool COthelloDlg::ifChangeUp(int x,int y){
	for(int i = 0 ; i < y ; i++){
		if(chesses[x][i] == curChess){
			for(int j = i + 1 ; j < y ; j++){
				if(chesses[x][j] != BLANK && chesses[x][j] != curChess)
					return true;
			}
		}
	}
	return false;
}
bool COthelloDlg::ifChangeDown(int x,int y){
	for(int i = 7 ; i >= y ; i--){
		if(chesses[x][i] == curChess){
			for(int j = i - 1 ; j >= y ; j--){
				if(chesses[x][j] != BLANK && chesses[x][j] != curChess)
					return true;
			}
		}
	}
	return false;
}
bool COthelloDlg::ifChangeLeft(int x,int y){
	for(int i = 0 ; i < x ; i++){
		if(chesses[i][y] == curChess){
			for(int j = i + 1 ; j < x ; j++){
				if(chesses[j][y] != BLANK && chesses[j][y] != curChess)
					return true;
			}
		}
	}
	return false;
}
bool COthelloDlg::ifChangeRight(int x,int y){
	for(int i = 7 ; i > x ; i--){
		if(chesses[i][y] == curChess){
			for(int j = i - 1 ; j > x ; j--){
				if(chesses[j][y] != BLANK && chesses[j][y] != curChess)
					return true;
			}
		}
	}
	return false;
}
bool COthelloDlg::ifChangeUpLeft(int x,int y){
	if(x >= y){
		for(int i = 0 ; i < y ; i++){
			if(chesses[x-y+i][i] == curChess){
				for(int j = i + 1 ; j < y ; j++){
					if(chesses[x-y+j][j] != BLANK && chesses[x-y+j][j] != curChess)
						return true;
				}
			}
		}
	}
	else{
		for(int i = 0 ; i < x ; i++){
			if(chesses[y-x+i][i] == curChess){
				for(int j = i + 1 ; j < x ; j++){
					if(chesses[y-x+j][j] != BLANK && chesses[y-x+j][j] != curChess)
						return true;
				}
			}
		}
	}
	return false;
}
bool COthelloDlg::ifChangeUpRight(int x,int y){
	if(y <= 7-x){
		for(int i = 0 ; i < y ; i++){
			if(chesses[x+y-i][i] == curChess){
				for(int j = i + 1 ; j < y ; j++){
					if(chesses[x+y-j][j] != BLANK && chesses[x+y-j][j] != curChess)
						return true;
				}
			}
		}
	}
	else{
		for(int i = 7 ; i > x ; i--){
			if(chesses[i][x+y-i] == curChess){
				for(int j = i - 1 ; j > x ; j--){
					if(chesses[x+y-j][j] != BLANK && chesses[x+y-j][j] != curChess)
						return true;
				}
			}
		}
	}
	return false;
}
bool COthelloDlg::ifChangeDownLeft(int x,int y){
	if(x > 7-y){
		for(int i = 7 ; i > 7 ; i--){
			if(chesses[x+y-i][i] == curChess){
				for(int j = i-1 ; j > x ; j--){
					if(chesses[x+y-j][j] != BLANK && chesses[x+y-j][j] != curChess)
						return true;
				}
			}
		}
	}
	else{
		for(int i = 0 ; i < x ; i++){
			if(chesses[x+y-i][i] == curChess){
				for(int j = i+1 ; j < x ; j++){
					if(chesses[x+y-j][j] != BLANK && chesses[x+y-j][j] != curChess)
						return true;
				}
			}
		}
	}
	return false;
}
bool COthelloDlg::ifChangeDownRight(int x,int y){
	if(x > y){
		for(int i = 7 ; i > x ; i--){
			if(chesses[i][i-(x-y)] == curChess){
				for(int j = i-1 ; j > x ; j--){
					if(chesses[j][j-(x-y)] != BLANK && chesses[j][j-(x-y)] != curChess)
						return true;
				}
			}
		}
	}
	else{
		for(int i = 7 ; i > y ; i--){
			if(chesses[i-(x-y)][i] == curChess){
				for(int j = i-1 ; j > x ; j--){
					if(chesses[j-(x-y)][j] != BLANK && chesses[j-(x-y)][j] != curChess)
						return true;
				}
			}
		}
	}
	return false;
}

///////////////////////////////////////////////////////////////////////////////
void COthelloDlg::changeUp(int x,int y){
	for(int i = 0 ; i < y ; i++){
		if(chesses[x][i] == curChess){
			for(int j = i + 1 ; j < y ; j++){
				if(chesses[x][j] != BLANK && chesses[x][j] != curChess)
					chesses[x][j] = curChess;
			}
		}
	}
	chesses[x][y] = curChess;
}
void COthelloDlg::changeDown(int x,int y){
	for(int i = 7 ; i >= y ; i--){
		if(chesses[x][i] == curChess){
			for(int j = i - 1 ; j >= y ; j--){
				if(chesses[x][j] != BLANK && chesses[x][j] != curChess)
					chesses[x][j] = curChess;
			}
		}
	}
	chesses[x][y] = curChess;
}

void COthelloDlg::changeLeft(int x,int y){
	for(int i = 0 ; i < x ; i++){
		if(chesses[i][y] == curChess){
			for(int j = i + 1 ; j < x ; j++){
				if(chesses[j][y] != BLANK && chesses[j][y] != curChess)
					chesses[j][y] = curChess;
			}
		}
	}
	chesses[x][y] = curChess;
}

void COthelloDlg::changeRight(int x,int y){
	for(int i = 7 ; i > x ; i--){
		if(chesses[i][y] == curChess){
			for(int j = i - 1 ; j > x ; j--){
				if(chesses[j][y] != BLANK && chesses[j][y] != curChess)
					chesses[j][y] = curChess;
			}
		}
	}
	chesses[x][y] = curChess;
}

void COthelloDlg::changeUpLeft(int x,int y){
	if(x >= y){
		for(int i = 0 ; i < y ; i++){
			if(chesses[x-y+i][i] == curChess){
				for(int j = i + 1 ; j < y ; j++){
					if(chesses[x-y+j][j] != BLANK && chesses[x-y+j][j] != curChess)
						chesses[x-y+j][j] = curChess;
				}
			}
		}
	}
	else{
		for(int i = 0 ; i < x ; i++){
			if(chesses[y-x+i][i] == curChess){
				for(int j = i + 1 ; j < x ; j++){
					if(chesses[y-x+j][j] != BLANK && chesses[y-x+j][j] != curChess)
						chesses[y-x+j][j] = curChess;
				}
			}
		}
	}
	chesses[x][y] = curChess;
}

void COthelloDlg::changeUpRight(int x,int y){
	if(y <= 7-x){
		for(int i = 0 ; i < y ; i++){
			if(chesses[x+y-i][i] == curChess){
				for(int j = i + 1 ; j < y ; j++){
					if(chesses[x+y-j][j] != BLANK && chesses[x+y-j][j] != curChess)
						chesses[x+y-j][j] = curChess;
				}
			}
		}
	}
	else{
		for(int i = 7 ; i > x ; i--){
			if(chesses[i][x+y-i] == curChess){
				for(int j = i - 1 ; j > x ; j--){
					if(chesses[x+y-j][j] != BLANK && chesses[x+y-j][j] != curChess)
						chesses[x+y-j][j] = curChess;
				}
			}
		}
	}
	chesses[x][y] = curChess;
}

void COthelloDlg::changeDownLeft(int x,int y){
	if(x > 7-y){
		for(int i = 7 ; i > 7 ; i--){
			if(chesses[x+y-i][i] == curChess){
				for(int j = i-1 ; j > x ; j--){
					if(chesses[x+y-j][j] != BLANK && chesses[x+y-j][j] != curChess)
						chesses[x+y-j][j] = curChess;
				}
			}
		}
	}
	else{
		for(int i = 0 ; i < x ; i++){
			if(chesses[x+y-i][i] == curChess){
				for(int j = i+1 ; j < x ; j++){
					if(chesses[x+y-j][j] != BLANK && chesses[x+y-j][j] != curChess)
						chesses[x+y-j][j] = curChess;
				}
			}
		}
	}
	chesses[x][y] = curChess;
}

void COthelloDlg::changeDownRight(int x,int y){
	if(x > y){
		for(int i = 7 ; i > x ; i--){
			if(chesses[i][i-(x-y)] == curChess){
				for(int j = i-1 ; j > x ; j--){
					if(chesses[j][j-(x-y)] != BLANK && chesses[j][j-(x-y)] != curChess)
						chesses[j][j-(x-y)] = curChess;
				}
			}
		}
	}
	else{
		for(int i = 7 ; i > y ; i--){
			if(chesses[i-(x-y)][i] == curChess){
				for(int j = i-1 ; j > x ; j--){
					if(chesses[j-(x-y)][j] != BLANK && chesses[j-(x-y)][j] != curChess)
						chesses[j-(x-y)][j] = curChess;
				}
			}
		}
	}
	chesses[x][y] = curChess;
}

bool COthelloDlg::isOver(){
	for(int i = 0 ; i < 8 ; i++){
		for(int j = 0 ; j < 8 ; j++){
			if(chesses[i][j] == BLANK)
				return false;
		}
	}
	return true;
}

void COthelloDlg::tellFinal(){
	for(int i = 0 ; i < 8 ; i++){
		for(int j = 0 ; j < 8 ; j++){
			if(chesses[i][j] == BLACK)
				black++;
			else if(chesses[i][j] == WHITE)
				white++;
		}
	}
	if(black >= white)
		MessageBox("The black wins!");
	else
		MessageBox("The white wins!");
}

void COthelloDlg::OnLButtonDown(UINT nFlags, CPoint point)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	int x = (point.x-align)/width;
	int y = (point.y-align)/width;
	
	if(chesses[x][y] != BLANK){
		MessageBox("double place!choose again");
		return;
	}

	if(x >= 0 && x <= 1 && y >= 0 && y <= 1){
		if(ifChangeRight(x,y))
			changeRight(x,y);
		else if(ifChangeDown(x,y))
			changeDown(x,y);
		else if(ifChangeDownRight(x,y))
			changeDownRight(x,y);
		else{
			MessageBox("Please choose again!");
			return;
		}
	}
	if(x >=6 && x <=7 && y >=0 && y <= 1){
		if(ifChangeLeft(x,y))
			changeLeft(x,y);
		else if(ifChangeDown(x,y))
			changeDown(x,y);
		else if(ifChangeDownLeft(x,y))
			changeDownLeft(x,y);
		else{
			MessageBox("Please choose again!");
			return;
		}
	}
	if(x >= 0 && x <= 1 && y >=6 && y <=7){
		if(ifChangeUp(x,y))
			changeUp(x,y);
		else if(ifChangeRight(x,y))
			changeRight(x,y);
		else if(ifChangeUpRight(x,y))
			changeUpRight(x,y);
		else{
			MessageBox("Please choose again!");
			return;
		}
	}
	if(x >= 6 && x <= 7 && y >=6 && y <= 7){
		if(ifChangeUp(x,y))
			changeUp(x,y);
		else if(ifChangeLeft(x,y))
			changeLeft(x,y);
		else if(ifChangeUpLeft(x,y))
			changeUpLeft(x,y);
		else{
			MessageBox("Please choose again!");
			return;
		}
	}
	if(x >= 2 && x <=5 && y >= 0 && y <= 1){
		if(ifChangeDown(x,y))
			changeDown(x,y);
		else if(ifChangeLeft(x,y))
			changeLeft(x,y);
		else if(ifChangeRight(x,y))
			changeRight(x,y);
		else if(ifChangeDownLeft(x,y))
			changeDownLeft(x,y);
		else if(ifChangeDownRight(x,y))
			changeDownRight(x,y);
		else{
			MessageBox("Please choose again!");
			return;
		}
	}
	if(x >= 2 && x <=5 && y >= 6 && y <= 7){
		if(ifChangeUp(x,y))
			changeUp(x,y);
		else if(ifChangeLeft(x,y))
			changeLeft(x,y);
		else if(ifChangeRight(x,y))
			changeRight(x,y);
		else if(ifChangeUpLeft(x,y))
			changeUpLeft(x,y);
		else if(ifChangeUpRight(x,y))
			changeUpRight(x,y);
		else{
			MessageBox("Please choose again!");
			return;
		}
	}
	if(x >= 0 && x <= 1 && y >=2 && y <= 5){
		if(ifChangeUp(x,y))
			changeUp(x,y);
		else if(ifChangeDown(x,y))
			changeDown(x,y);
		else if(ifChangeRight(x,y))
			changeRight(x,y);
		else if(ifChangeUpRight(x,y))
			changeUpRight(x,y);
		else if(ifChangeDownRight(x,y))
			changeDownRight(x,y);
		else{
			MessageBox("Please choose again!");
			return;
		}
	}
	if(x >= 6 && x <= 7 && y >=2 && y <= 5){
		if(ifChangeUp(x,y))
			changeUp(x,y);
		else if(ifChangeDown(x,y))
			changeDown(x,y);
		else if(ifChangeLeft(x,y))
			changeLeft(x,y);
		else if(ifChangeUpLeft(x,y))
			changeUpLeft(x,y);
		else if(ifChangeDownLeft(x,y))
			changeDownLeft(x,y);
		else{
			MessageBox("Please choose again!");
			return;
		}
	}
	if(x >= 2 && x <= 5 && y >= 2 && y <= 5){
		if(ifChangeUp(x,y))
			changeUp(x,y);
		else if(ifChangeDown(x,y))
			changeDown(x,y);
		else if(ifChangeLeft(x,y))
			changeLeft(x,y);
		else if(ifChangeRight(x,y))
			changeRight(x,y);
		else if(ifChangeUpLeft(x,y))
			changeUpLeft(x,y);
		else if(ifChangeUpRight(x,y))
			changeUpRight(x,y);
		else if(ifChangeDownLeft(x,y))
			changeDownLeft(x,y);
		else if(ifChangeDownRight(x,y))
			changeDownRight(x,y);
		else if(ifChangeDownLeft(x,y))
			changeDownLeft(x,y);
		else{
			MessageBox("Please choose again!");
			return;
		}
	}
	if(curChess == BLACK)
		curChess = WHITE;
	else if(curChess == WHITE)
		curChess = BLACK;
	Invalidate();

	CDialogEx::OnLButtonDown(nFlags, point);
}
