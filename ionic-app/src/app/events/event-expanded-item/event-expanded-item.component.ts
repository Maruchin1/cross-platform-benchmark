import {Component, ElementRef, Input, OnInit, Output, ViewChild, EventEmitter} from '@angular/core';
import {Event} from '../../core/event.model';

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

  @Output() scrollTo = new EventEmitter<[number, number]>();

  ngOnInit() {
    setTimeout(() => {
      const x = this.imageTopBorder.nativeElement.offsetLeft;
      const y = this.imageTopBorder.nativeElement.offsetTop;
      this.scrollTo.emit([x, y]);
    }, 100);
  }

}
