import {Component, Input, OnInit} from '@angular/core';
import {Event} from '../../core/event.model';

@Component({
  selector: 'app-event-expanded-item',
  templateUrl: './event-expanded-item.component.html',
  styleUrls: ['./event-expanded-item.component.scss'],
})
export class EventExpandedItemComponent implements OnInit {
  constructor() {
  }

  @Input() event: Event;

  ngOnInit() {
  }

}
