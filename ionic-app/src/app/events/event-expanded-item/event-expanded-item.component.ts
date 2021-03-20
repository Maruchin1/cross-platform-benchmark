import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {Event} from '../../core/event.model';
import {IonImg} from '@ionic/angular';

@Component({
  selector: 'app-event-expanded-item',
  templateUrl: './event-expanded-item.component.html',
  styleUrls: ['./event-expanded-item.component.scss'],
})
export class EventExpandedItemComponent implements OnInit {
  constructor() {
  }

  @ViewChild('imageTopBorder', {static: true}) imageTopBorder: ElementRef;

  @Input() event: Event;

  ngOnInit() {
    this.imageTopBorder.nativeElement.scrollIntoView();
  }

}
