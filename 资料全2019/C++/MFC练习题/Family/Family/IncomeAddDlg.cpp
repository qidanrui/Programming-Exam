// IncomeAddDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "Family.h"
#include "IncomeAddDlg.h"
#include "afxdialogex.h"


// IncomeAddDlg 对话框

IMPLEMENT_DYNAMIC(IncomeAddDlg, CDialogEx)

IncomeAddDlg::IncomeAddDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(IncomeAddDlg::IDD, pParent)
	, in_add_name(_T(""))
	, in_add_come(_T(""))
	, in_add_money(0)
	, in_add_year(_T(""))
	, in_add_month(_T(""))
	, in_add_day(_T(""))
{
	
}

IncomeAddDlg::~IncomeAddDlg()
{
}

void IncomeAddDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_IN_ADD_NAME, in_add_name);
	DDX_Text(pDX, IDC_IN_ADD_COME, in_add_come);
	DDX_Text(pDX, IDC_IN_ADD_MONEY, in_add_money);
	DDX_Control(pDX, IDC_IN_ADD_YEAR, in_add_year_ctl);
	DDX_Control(pDX, IDC_IN_ADD_MONTH, in_add_month_ctl);
	DDX_Control(pDX, IDC_IN_ADD_DAY, in_add_day_ctl);
	DDX_CBString(pDX, IDC_IN_ADD_YEAR, in_add_year);
	DDX_CBString(pDX, IDC_IN_ADD_MONTH, in_add_month);
	DDX_CBString(pDX, IDC_IN_ADD_DAY, in_add_day);
}


BEGIN_MESSAGE_MAP(IncomeAddDlg, CDialogEx)
END_MESSAGE_MAP()


// IncomeAddDlg 消息处理程序


BOOL IncomeAddDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// TODO:  在此添加额外的初始化
	CString year;
	for(int i = 2009 ; i <= 2013 ; i++){
		year.Format("%d",i);
		in_add_year_ctl.AddString(year);
	}
	CString month;
	for(int i = 1 ; i <= 12 ; i++){
		month.Format("%d",i);
		in_add_month_ctl.AddString(month);
	}
	CString day;
	for(int i = 1 ; i <= 31 ; i++){
		day.Format("%d",i);
		in_add_day_ctl.AddString(day);
	}

	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}

void IncomeAddDlg::inputData(vector<income> in,int index){
	this->in_add_name.Format("%s",in[index-1].name.c_str());
	this->in_add_come.Format("%s",in[index-1].come.c_str());
	this->in_add_year.Format("%s",in[index-1].year.c_str());
	this->in_add_month.Format("%s",in[index-1].month.c_str());
	this->in_add_day.Format("%s",in[index-1].day.c_str());
	this->in_add_money = in[index-1].money;
}
