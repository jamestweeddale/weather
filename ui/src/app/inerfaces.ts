interface Station {
    id: number;
    name: string;
    uuid: string;
}

interface Reading {
    id: number;
    name: string;
    value: number;
    units: string;
    time: Date;
}


interface ReadingKey {
    id: number;
    name: string;
    type: string;
}