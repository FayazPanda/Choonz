jmeter -f -n -t pageTESTS\stress\STRESSalbumPageTEST.jmx -l csv\stressCSV\STRESSalbumPage.csv -e -o html\stress\STRESSalbumPage && jmeter -f -n -t pageTESTS\stress\STRESSbrowsePageTEST.jmx -l csv\stressCSV\STRESSbrowsePage.csv -e -o html\stress\STRESSbrowsePage && jmeter -f -n -t pageTESTS\stress\STRESSloginPageTEST.jmx -l csv\stressCSV\STRESSloginPage.csv -e -o html\stress\STRESSloginPage && jmeter -f -n -t pageTESTS\stress\STRESSplaylistPageTEST.jmx -l csv\stressCSV\STRESSplaylistPage.csv -e -o html\stress\STRESSplaylistPage