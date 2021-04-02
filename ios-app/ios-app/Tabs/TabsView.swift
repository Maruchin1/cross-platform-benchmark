//
//  TabsView.swift
//  ios-app
//
//  Created by admin on 01/04/2021.
//

import SwiftUI

struct TabsView: View {
    @StateObject var repository = Repository(webApi: WebApi())
    
    var body: some View {
        GeometryReader { geometry in
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
            .environmentObject(repository)
        }
    }
}

struct TabsView_Previews: PreviewProvider {
    static var previews: some View {
        TabsView()
    }
}
