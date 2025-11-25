# https://medium.com/@prashant558908/low-level-design-of-a-parking-lot-in-python-using-strategy-design-pattern-2d0ca9a1c8d1
# https://chatgpt.com/share/6925ba0d-4fac-8000-8c33-d0020a888223
class ParkingSpot:
    def __init__(self,spot_id:str,vehicle_type:int):
        self.spot_id=spot_id
        self.vehicle_type=vehicle_type
        self.parked=False

    def is_parked(self)->bool:
        return self.parked
    
    def park_vehicle(self):
        self.parked=True

    def remove_vehicle(self):
        self.parked=False

    def get_spot_id(self)->bool:
        return self.spot_id
    
    def get_vehicle_type(self)->int:
        return self.vehicle_type


class ParkingFloor:
    # this will have a floor number, floor layout ,
    def __init__(self,floor_index:int,layout:list[list[int]],vehicle_types:list[int]):
        rows=len(layout)
        cols=len(layout[0])
        self.parking_spots=[[None]*cols for _ in range(rows)]    #this will build a 2d array with None as elements 

        self.free_spots_count={vt:0 for vt in vehicle_types}   #{2:0,4:0}

        # now we will create the floor with ParkingSpot objects
        for r in range(rows):
            for c in range(cols):
                if layout[r][c]!=0:
                    vt=layout[r][c]  # getting the vehicle type
                    self.parking_spots[r][c]= ParkingSpot(f"{floor_index}-{r}-{c}",vt)
                    self.free_spots_count[vt]+=1

    def get_free_spots_count(self,vehicle_type:int)->int:
        return self.free_spots_count.get(vehicle_type,0)
    
    def park(self,vehicle_type:int)->str:
        # print("line 45")
        if self.free_spots_count[vehicle_type]==0:   #if no available spot return empty string
            return ""  
        # print("line 48",self.parking_spots)
        
        for r in range(len(self.parking_spots)):
            for c in range(len(self.parking_spots[r])):
                spot=self.parking_spots[r][c]
                if spot and (spot.is_parked()==False and spot.get_vehicle_type()==vehicle_type):
                    spot.park_vehicle()
                    self.free_spots_count[vehicle_type]-=1
                    return spot.get_spot_id()
        return ""
    
    def remove_vehicle(self,row:int,col:int)->bool:
        # add a check if valid row and col
        if row<0 or row>= len(self.parking_spots) or col<0 or col>=len(self.parking_spots[0]):
            return False
        
        spot=self.parking_spots[row][col]
        if spot is None or spot.is_parked==False:
            return False
        
        vt=spot.get_vehicle_type()
        spot.remove_vehicle()
        self.free_spots_count[vt]+=1
        return True



class SearchManager:

    def __init__(self):
        self.index_map={}

    def index(self,vehicle_number:str,ticket_id:str,spot_id:str):
        self.index_map[vehicle_number]=spot_id
        self.index_map[ticket_id]=spot_id

    def search(self,query):
        return self.index_map.get(query,"")
    

class ParkingStrategy:   #class which can be implemented by different strategies

    def park(self,vehicle_type:int,floors)->str:
        raise NotImplementedError
    

class NearestParkingStrategy(ParkingStrategy):

    def park(self,vehicle_type:int,floors)->str:
        # print("line 95", vehicle_type, floors)
        for floor in floors:
            # print("line 97",floor)
            spot = floor.park(vehicle_type)
            if spot:
                return spot
        return ""
    

class MostFreeSpotsParkingStrategy(ParkingStrategy):
    
    def park(self,vehicle_type:int,floors)->str:
        best_idx=-1
        best_count=0
        for i,floor in enumerate(floors):
            count = floor.get_free_spots_count(vehicle_type)
            if count>best_count:
                best_count=count
                best_idx=i
        if best_idx>-1:
            spot = floors[best_idx].park(vehicle_type)
            if spot:
                return spot

        return ""
    

class ParkManager:
    def __init__(self):
        self.algorithms=[NearestParkingStrategy(),MostFreeSpotsParkingStrategy()]

    def park(self,floors,vehicle_type:int,strategy_idx:int)->str:
        # print("line 125", vehicle_type, floors,self.algorithms[strategy_idx])
        if 0<=strategy_idx<len(self.algorithms):
            # print("valid parking strategy",strategy_idx)
            return self.algorithms[strategy_idx].park(vehicle_type,floors)
        return ""
    

class Solution:
    def init(self,helper,parking:list[list[list[int]]]):
        self.vehicle_types=[2,4]
        self.helper=helper
        self.park_manager=ParkManager()
        self.search_manager=SearchManager()
        self.floors=[ParkingFloor(i,parking[i],self.vehicle_types) for i in range(len(parking))]

    def park(self,vehicle_type,vehicle_number,ticket_id,parking_strategy):
        spot_id=self.park_manager.park(self.floors,vehicle_type,parking_strategy)
        # print("line 140",spot_id)
        if spot_id:
            self.search_manager.index(vehicle_number,ticket_id,spot_id)
        return spot_id

    def remove_vehicle(self,spot_id):
        f,r,c=map(int,spot_id.split('-'))
        return self.floors[f].remove_vehicle(r,c)

    def get_free_spots_count(self,floor,vehicle_type:int):
        return self.floors[floor].get_free_spots_count(vehicle_type)

    def search_vehicle(self,query):
        return self.search_manager.search(query)


class Helper:
    def println(self, *args): print(*args)

if __name__=="__main__":
    print("System running")
    helper=Helper()
    parking = [
       [[2,0,4],[2,4,4]],
       [[4,4,2],[0,2,0]]
    ]
    sol = Solution()
    sol.init(helper, parking)


    print("first vehicle")
    spot =sol.park(2,"1stvehicle","T001",0)
    print("parked at: ", spot)
    print("Search:", sol.search_vehicle("1stvehicle"))
    # print("Remove success:", sol.remove_vehicle(spot))
    # print("Free spots floor0 2-wheeler:", sol.get_free_spots_count(0,2))

    print("second vehicle")

    spot =sol.park(2,"2ndvehicle","T002",0)
    print("parked at: ", spot)
    print("Search:", sol.search_vehicle("2ndvehicle"))
    print("Remove success:", sol.remove_vehicle(spot))
    print("Free spots floor0 2-wheeler:", sol.get_free_spots_count(0,2))
    



    




        


