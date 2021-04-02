//
//  EventsPage.swift
//  ios-app
//
//  Created by admin on 01/04/2021.
//

import SwiftUI

struct EventsPage<T : RepositoryProtocol>: View {
    @EnvironmentObject var repository: T
    
    var body: some View {
        List {
            HStack {
                Spacer()
                Text("25-lecie\nWydziału Grafiki\ni Sztuki Mediów")
                    .fixedSize(horizontal: false, vertical: true)
                    .font(.largeTitle)
                    .multilineTextAlignment(.center)
                    .padding(.top, 156)
                    .padding(.bottom, 100)
                Spacer()
            }
            Section(
                header: HStack {
                    Spacer()
                    Text("Wydarzenia")
                        .font(.title2)
                    Spacer()
                }.frame(height: 56)
            ) {
                Spacer()
                    .frame(height: 48)
                ForEach(repository.events) { event in
                    EventItemView(event: event)
                        .padding(.horizontal, 16)
                        .padding(.bottom, 24)
                }
            }
            .textCase(nil)
            .background(Color.white)
            .listRowInsets(EdgeInsets(
                            top: 0,
                            leading: 0,
                            bottom: 0,
                            trailing: 0))
            HStack{
                Spacer()
                Text("Loading ...")
                    .onAppear(perform: {
                        repository.loadNextEventsPage()
                    })
                Spacer()
            }
        }
    }
}

struct EventsPage_Previews: PreviewProvider {
    static var previews: some View {
        EventsPage<RepositoryMock>().environmentObject(RepositoryMock())
    }
}
