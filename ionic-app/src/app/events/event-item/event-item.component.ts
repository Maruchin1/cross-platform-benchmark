import {Component, Input, OnInit} from '@angular/core';
import {Event} from '../../core/event.model';

@Component({
  selector: 'app-event-item',
  templateUrl: './event-item.component.html',
  styleUrls: ['./event-item.component.scss'],
})
export class EventItemComponent implements OnInit {
  constructor() {
  }

  @Input() event: Event;

  ngOnInit() {
  }

}
