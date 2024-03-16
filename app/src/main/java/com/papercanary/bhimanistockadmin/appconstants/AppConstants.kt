package com.papercanary.bhimanistockadmin.appconstants

object AppConstants {
    const val PAGE_SIZE = 4

    object Dashboard {
        object BottomNavBar {
            const val BOTTOM_NAV_TITLE1 = "IPO"
            const val BOTTOM_NAV_TITLE2 = "Insurance"
            const val BOTTOM_NAV_TITLE3 = "More"
        }

        object IPOTabs {
            const val TAB1 = "Mainline"
            const val TAB2 = "SME"
        }

        object InsuranceTabs {
            const val TAB1 = "Insurance"
            const val TAB2 = "Mediclaim"
        }
    }

    object MoreScreen {
        const val MORE1 = "Mutual Fund"
        const val MORE2 = "PMS"
        const val MORE3 = "Fixed Income"
    }

    object AddIpoScreen {
        const val COMPANY_NAME = "Company Name"
        const val COMPANY_IMAGE_URL = "Company Image Url"
        const val OFFER_START_PRICE = "Offer Start Price"
        const val OFFER_END_PRICE = "Offer End Price"
        const val LOT_SIZE = "Lot Size"
        const val START_DATE_TITLE = "Select Offer Start Date"
        const val END_DATE_TITLE = "Select Offer End Date"
        const val ADD_IPO = "Add IPO"
        const val EMPTY_ERROR_MSG = "Empty"
    }

    object AddGeneral {
        const val ADD_BUTTON = "Add"
    }

    object Firestore {
        object Collections {
            const val MAINLINE_IPOS = "mainline_ipos"
            const val SME_IPOS = "sme_ipos"
            const val INSURANCES = "insurances"
            const val MEDICLAIMS = "mediclaims"
            const val MUTUAL_FUNDS = "mutual_funds"
            const val PMS = "pms"
            const val FIXED_INCOME = "fixed_income"
        }

        object IpoS {
            const val OFFER_START_DATE = "offerStartDate"
        }

        object General {
            const val COMPANY_NAME = "companyName"
            const val COMPANY_IMAGE = "companyImage"
            const val OFFER_START_PRICE = "offerStartPrice"
            const val OFFER_END_PRICE = "offerEndPrice"
        }
    }
}