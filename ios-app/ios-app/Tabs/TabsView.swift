//
//  TabsView.swift
//  ios-app
//
//  Created by admin on 01/04/2021.
//

import SwiftUI

struct TabsView: View {
    @StateObject var repository = Repository(webApi: WebApi(), localDatabase: LocalDatabase())
    
    init() {
        UITabBar.appearance().backgroundColor = UIColor(Color("Grey300"))
    }
    
    var body: some View {
        TabView {
            EventsPage<Repository>()
                .tabItem {
                    Text("wydarzenia")
                }
            MessagesPage()
                .tabItem {
                    Text("komuniakty")
                }
            DepartmentPage()
                .tabItem {
                    Text("wydział")
                }
            TimelinePage()
                .tabItem {
                    Text("25-lat")
                }
        }
        .padding(.top, 1)
        .accentColor(Color.black)
        .environmentObject(repository)
    }
}

struct TabsView_Previews: PreviewProvider {
    static var previews: some View {
        TabsView()
    }
}
