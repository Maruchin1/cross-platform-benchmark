import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EventsPage } from './events.page';
import { ExploreContainerComponentModule } from '../explore-container/explore-container.module';

import { Tab1PageRoutingModule } from './events-routing.module';
import {EventItemComponent} from './event-item/event-item.component';
import {EventExpandedItemComponent} from './event-expanded-item/event-expanded-item.component';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    ExploreContainerComponentModule,
    Tab1PageRoutingModule
  ],
  declarations: [EventsPage, EventItemComponent, EventExpandedItemComponent]
})
export class Tab1PageModule {}
