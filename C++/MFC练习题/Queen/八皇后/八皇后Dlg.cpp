
// 八皇后Dlg.cpp : 实现文件
//

#include "stdafx.h"
#include "八皇后.h"
#include "八皇后Dlg.h"
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


// C八皇后Dlg 对话框




C八皇后Dlg::C八皇后Dlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(C八皇后Dlg::IDD, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void C八皇后Dlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(C八皇后Dlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_WM_LBUTTONDOWN()
END_MESSAGE_MAP()


// C八皇后Dlg 消息处理程序

BOOL C八皇后Dlg::OnInitDialog()
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
	for(int i = 0 ; i < 8 ; i ++){
		for(int j = 0 ; j < 8 ; j++){
			chesses[i][j] = BLANK;
		}
	}
	align = 20;
	width = 30;
	count = 0;
	//chesses[3][0]=chesses[4][0]=BLACK;
	return TRUE;  // 除非将焦点设置到控件，否则返回 TRUE
}

void C八皇后Dlg::OnSysCommand(UINT nID, LPARAM lParam)
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

void C八皇后Dlg::OnPaint()
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
			MessageBox("win");
	}
}

//当用户拖动最小化窗口时系统调用此函数取得光标
//显示。
HCURSOR C八皇后Dlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}

void C八皇后Dlg::drawChessBoard(){
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
void C八皇后Dlg::drawChess(){
	CClientDC dc(this);
	CBrush brush_black(RGB(0,0,0));
	CBrush brush_red(RGB(255,0,0));
	for(int i = 0 ; i < 8 ; i++){
		for(int j = 0 ; j < 8 ; j++){
			if(chesses[j][i] == BLACK){
				dc.SelectObject(brush_black);
				dc.Ellipse(align+width*j,align+width*i,align+width*(j+1),align+width*(i+1));
			}
			else if(chesses[j][i] == RED){
				dc.SelectObject(brush_red);
				dc.Ellipse(align+width*j,align+width*i,align+width*(j+1),align+width*(i+1));
			}
		}
	}
}

bool C八皇后Dlg::isOver(){
	int num = 0;
	for(int i = 0 ; i < 8 ; i++){
		for(int j = 0 ; j < 8 ; j++){
			if(chesses[j][i] == BLACK)
				num++;
		}
	}
	if(num == 8)
		return true;
	else
		return false;
}

bool C八皇后Dlg::canPlace(int x,int y){
	POSTION pos;
	//上
	for(int i = 0 ; i < y ; i++){
		if(chesses[x][i] == BLACK)
		{
			pos.x = x;
			pos.y = i;
			red_vec.push_back(pos);
		
		}
	}
	//下
	for(int i = 7 ; i > y ; i--){
		if(chesses[x][i] == BLACK)
		{
			pos.x = x;
			pos.y = i;
			red_vec.push_back(pos);
		
		}
	}
	//左
	for(int i = 0 ; i < x ; i++){
		if(chesses[i][y] == BLACK)
		{
			pos.x = i;
			pos.y = y;
			red_vec.push_back(pos);
			
		}
	}
	//右
	for(int i = 7 ; i > x ; i--){
		if(chesses[i][y] == BLACK)
		{
			pos.x = i;
			pos.y = y;
			red_vec.push_back(pos);
			
		}
	}
	//左上
	if(x > y){
		for(int i = 0 ; i < y ; i++){
			if(chesses[x-y+i][i] == BLACK)
			{
				pos.x = x-y+i;
				pos.y = i;
				red_vec.push_back(pos);
				
			}
		}
	}
	else{
		for(int i = 0 ; i < x ; i++){
			if(chesses[i][y-x+i] == BLACK)
			{
				pos.x = i;
				pos.y = y-x+i;
				red_vec.push_back(pos);
				
			}
		}
	}
	//左下
	if((x+y)<=7){
		for(int i = 0 ; i < x ; i++){
			if(chesses[i][x+y-i] == BLACK)
			{
				pos.x = i;
				pos.y = x+y-i;
				red_vec.push_back(pos);
				
			}
		}
	}
	else{
		for(int i = 7 ; i > y ; i--){
			if(chesses[x+y-i][i] == BLACK)
			{
				pos.x = x+y-i;
				pos.y = i;
				red_vec.push_back(pos);
				
			}
		}
	}
	//右上
	if((x+y)<=7){
		for(int i = 0 ; i < y ; i++){
			if(chesses[x+y-i][i] == BLACK)
			{
				pos.x = x+y-i;
				pos.y = i;
				red_vec.push_back(pos);
				
			}
		}
	}
	else{
		for(int i = 7 ; i > x ; i--){
			if(chesses[i][x+y-i] == BLACK)
			{
				pos.x = i;
				pos.y = x+y-i;
				red_vec.push_back(pos);
				
			}
		}
	}
	//右下
	if(x > y){
		for(int i = 7 ; i > x ; i--){
			if(chesses[i][i-(x-y)] == BLACK)
			{
				pos.x = i;
				pos.y = i-(x-y);
				red_vec.push_back(pos);
				
			}
		}
	}
	else{
		for(int i = 7 ; i > x ; i--){
			if(chesses[i-(y-x)][i] == BLACK)
			{
				pos.x = i-(y-x);
				pos.y = i;
				red_vec.push_back(pos);
				
			}
		}
	}
	int size = red_vec.size();
	if(red_vec.size()!=0)
		return false;
	else
		return true;
}
bool C八皇后Dlg::isLose(int x,int y){
	for(int i = 0 ; i < 7 ; i++){
		if(canPlace(i,y))
			return false;
	}
	return true;
}
void C八皇后Dlg::OnLButtonDown(UINT nFlags, CPoint point)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	for(int i = 0 ; i < red_vec.size() ; i++){
		chesses[red_vec[i].x][red_vec[i].y] = BLACK;
	}
	Invalidate();
	red_vec.clear();

	int x = (point.x-align)/width;
	int y = (point.y-align)/width;

	if(isLose(x,y)){
		MessageBox("Lose");
		return;
	}
	
	if(canPlace(x,y)){
		chesses[x][y] = BLACK;
	}
	else{
		for(int i = 0 ; i < red_vec.size() ; i++){
			chesses[red_vec[i].x][red_vec[i].y] = RED;
		}
	}
	Invalidate();

	
	
	CDialogEx::OnLButtonDown(nFlags, point);
}
