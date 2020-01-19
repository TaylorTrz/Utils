package leetcode.swapForLongestRepeatedCharacterSubstring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @detail 单字符重复子串的最大长度
 * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 * 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 * @thoughts day1
 * 单字符重复：从一个点出发，找到跟自己不同的点，就是最长。然后再从这个点出发，找到下一个不同的点。直至字符串尾
 * 交换：交换两个相同的字符毫无意义，所以记录下所有不同的字符的位置，然后依次交换，如果字符相同则不交换。
 * 超出时间限制...
 * @improvement day3
 * 1. 执行遍历1次，并添加非重复字符节点
 * 2. 根据记录的节点，而是以left/right节点，向左向右遍历至非重复节点
 * 3. 最终目的是取Max
 */
public class Solution {
    private List<Integer> list = new ArrayList<>();
    private Object[] location;

    public int maxRepOpt1(String text) {
        int maxLength = getMaxLengthOnce(text);
        location = list.toArray();

        // 不交换，直接在两个节点位置向左向右遍历至非重复字符
        for (int i = 0; i < location.length; i++)
            for (int j = 0; j < location.length; j++) {
                // 字符相同不交换
                if (text.charAt((int) location[i]) != text.charAt((int) location[j])) {
                    int swappedLength = getSwappedLength(text, (int) location[i], (int) location[j]);
                    maxLength = Math.max(maxLength, swappedLength);
                }
            }
        return maxLength;
    }

    public int getMaxLengthOnce(String text) {
        int left = 0;
        int right = 0;
        int maxLength = 0;

        // 找到最大不重复子串的长度,并记录下非重复字符的位置
        list.add(0);
        while (right != text.length() && left < text.length()) {
            if (text.charAt(left) != text.charAt(right)) {
                left = right;
                list.add(left);
            }
            right++;
            maxLength = Math.max(maxLength, (right - left));
        }
        return maxLength;
    }

    // 当left与right位置的字符进行交换，只考虑原left位置的最大非重子串长度
    public int getSwappedLength(String text, int left, int right) {
        int maxLength = 0;
        int l;
        int r;

        // 向左循环
        for (l = left - 1; l >= 0; l--) {
            if (text.charAt(right) != text.charAt(l) || l == right) {
                break;
            }
        }

        // 向右循环
        for (r = left + 1; r < text.length(); r++) {
            if (text.charAt(right) != text.charAt(r) || r == right) {
                break;
            }
        }

        return r - l - 1;
    }




    /**
     * 滑动窗口法：
     * 1. 建立字典数组count，记录26个小写字符的出现次数
     * 2. 设置长度数组length，记录索引i左侧重复字符个数以及右侧重复字符个数，分3种情况进行讨论：
     * 获取左侧重复字符总数left，判断left < count[左侧字符]，则left++  -> 获取left
     * 获取右侧重复字符总数right，判断right < count[右侧字符]，则right++  -> 获取right
     * 判断左右两侧字符相同：是的话left + right + 1，否的话 max(left, right) -> 获取length[i]
     * 3. 直接length.max()
     *
     * @author taoruizhe
     * @date 2020/01/19
     */
    public int maxRepOpt2(String text) {
        int[] count = new int[26];
        int[] length = new int[text.length()];

        // 字典数组
        for (int i = 0; i < text.length(); i++) {
            int integer = text.charAt(i) - 97;
            count[integer]++;
        }

        // 滑动窗口获取长度数组
        for (int pivot = 0; pivot < text.length(); pivot++) {
            if (pivot == 0)
                getRightLength(text, count, length);
            if (pivot == text.length() - 1)
                getLeftLength(text, count, length);
            if (pivot != 0 && pivot != text.length() -1)
                getLength(text, count, length, pivot);
        }

        int maxLength = 0;
        for (int value : length) {
            maxLength = Math.max(maxLength, value);
        }
        return maxLength;
    }


    public void getLeftLength(String text, int[] count, int[] length) {
        int left = 0;
        int l;
        int location = text.length() - 1 - 1;
        for (l = location; l >= 0; l--) {
            if (text.charAt(location) != text.charAt(l)) {
                break;
            }
        }
        left = location - l - 1;

        if (left < count[text.charAt(location) - 97])
        left++;
        length[location + 1] = left;
    }


    public void getRightLength(String text, int[] count, int[] length) {
        int right = 0;
        int r;
        for (r = 1; r < text.length(); r++) {
            if (text.charAt(1) != text.charAt(r)) {
                break;
            }
        }
        right = r - 1;

        if (right < count[text.charAt(1) - 97])
            right++;
        length[0] = right;
    }


    public void getLength(String text, int[] count, int[] length, int pivot) {
        int left = 0;
        int right = 0;
        // 找左侧子串
        int l;
        for (l = pivot - 1; l >= 0; l--) {
            if (text.charAt(pivot - 1) != text.charAt(l)) {
                break;
            }
        }
        left = pivot - l - 1;
        // 找右侧子串
        int r;
        for (r = pivot + 1; r < text.length(); r++) {
            if (text.charAt(pivot + 1) != text.charAt(r)) {
                break;
            }
        }
        right = r - pivot - 1;

        // 情况3：若非头非尾，且左右字符相同
        if (text.charAt(pivot - 1) == text.charAt(pivot + 1)) {
            length[pivot] = left + right ;
            // 字符总数要大于左右连续字符串长度的和
            if (left + right < count[text.charAt(pivot - 1) - 'a'])
                length[pivot] = left + right + 1;
        } else {
            // 情况1：若非头，且左侧字符子串长度小于字符总数
            if (left < count[text.charAt(pivot - 1) - 97])
                left++;
            // 情况2：若非尾，且右侧字符子串长度小于字符总数
            if (right < count[text.charAt(pivot + 1) - 97])
                right++;
            length[pivot] = Math.max(left, right);
        }

    }


    public static void main(String[] args) {
        System.out.println(new Solution().maxRepOpt2("yoshzytimlarifesuusdfjimslibkwdeumwgjlbgtnqwbaxkshjrnzsietrqslramhoxdrzcabnmbypktgstckiijvnjejayavqosobqhwbpktwxpjhmbbfvosilwacvqjyumwujhysvwnspzryksphijbxhnvknlsfjuztkhoqmauitgdsishshtfkgycbzoqfssutsbvkogysxxmcyrubkutwlaatgprgpmgnbrlnxnrfgbjzupasjissvvrwfxbdlwzdxezysnxibhnocbsyyycuxxaccejkzsyhrpcawauxxzfincbdfaxbmqtjfzrbcwrkrqqlpzxifhtgsenkdurwfbfoiswzzmhjvvtelqrsgldwscqtaszfvufdxghasdcgpqntlhnglqknymepgmfzjayqstnyprbzkjvvpkdutkyptekqrxwaguepzgbfjumgpzgykrjccknvskhwypckfcvyfzpievtcbgugbriwrramcqmnxinoctftmfbiomqlyhwejnbgvgoeohhhjpewusrvszottyxpaljohkwuyanjyujxwrxbwaohrjyimmapgrhlxggxzcrtxgqujkehdyrqhqshdkgdphhasgqqoqpidxrkeuahxtykepqlooiqjqmatcxmbjsbnisqnzzqxranllrncpmmfexunthpjineelijnuemjgjtfrdommizorfoqaeqlrllfajtixooqisbytfgzfotaogarglhdlicyzzanhdjohjmqkjaxryghbysxqntfbgzmhditabvfkgmakiwwzocatqvhusznpzrqwcmadzrwlokmdmbsuboywwwctdofmndytgzonbgyrzbezlkxnspiqskuxftvaxtylyssbjgaxbpqnvrwpjwdfalnvpxbeqiyzayetkymdbtizooukqqtzqyfcyeqwoqfxuoihumeqnquoutjzswftsvqqfrjeuzbcvmrdgbbhfadpiovyrlfsgobthxvhenfkssvknqvufcdwkmjufeyaobwilciuompybewsdlpnyfghswbvdlmcyrcyivkitqdrkknfefvtqbzbdmodtpcgtdrkfpxsspsswgokxlxgcqvshxmoxknzipwspraiuzuydoqchhaoiqqzwjpmackjqcstyvtditgfkqwpcsjnzawfzikkptboovulhljjuwjruxtatkclcbbiztptxzxlwazbrziqcyoraqxpdblbvrvsxcquhkooyqbeptolmlicwarurndryjhqynlyydwbkurihertwakpvbkodpajdmuepefgnrbvdddkhmncvtosiqixokmtfqmvpxrplmylwkughvyhceeiimraxsakvqurfoxtaxjtmkzpaiurmobkvjcsaumwexjjtnewefcrpbynlzvviqbyeeuibhrfltipklpkcutaiibyfdcdsezdnsnleyuhwzgaiqbvvyoivdvbaoybkllguwlcfkgqyyitcdwrwqlvoajkdzhxfdqenogmrbcovrvnrbwwftmgyzarqcebivxuevmnuyuyubdrawantpubrvmcxgntgdrwvkutlylxyyfgazzxltqwlduwcxaqzalabeqjgqbchljdkzxzrtcmuksyuwersjmcnykslxhvdnbqzlzcwmeycfizodgsdhqabatptkvukxenzneltwcdcvhynblsgqsybxmypczwmciwzweuclmayoobflggaboxjkiubfnyjjewzpltnpfccofezdsavmsmhmbyvtvliswzufpgspoxzszduwxtztuwfumhybzxxvauoddmeydfyabjelsjdxrqfwjhyveoudpmdpugwuafntnzhyukualflbuwtndowulisytcmpekydzubmaoxfcwsqrxizenhlnwifcndyjcgllymceweoinxtcjibrdzdunlvfnvczifytzxdzcbpaodexjiovhsqbrmuydrikishqqlifehtkozhxcbjdebukquuzjyueixbqrmmfzphikgjxdnrontcqthxbuzmzgrnnbpvngdidfvanxiblvcihvrmkuhxoouzwzdfatswadnnhdzgdkyxllunumjnbfrafvvyvlwktcdajzuditkolbbebzciiahioqgddnruwpyzhysamfebgzwghjxqyhipyaytrcwpsdiniigvjnkzxbocsfososuunftecflvqebmqbubljvicgiwqvytstmthncfpzryyfnmwtugaerbpwtchijbhhqbnpriputzezqeecprpmligkthywttfriroqkgsdvkojdiovxbusjesywmnpletxkhgiqhokmubpsibqbcrohofmjugdfhoaadhbyykrzcsiwboonopddrkoscsrqctgqvjtlakrtpznisphliceygckykrigqqgtxuryymkksmyzkxywiaprsoidzdqnivwavderiwdkscjcyzqtgjqfkxcflsadbijuszlesmqsykzyultvqxuspiuibxlqpduxoikydtmqkmffmyewqylrykwwntlmokwdxcwxbuzqrdwssgdeicwnrnflsqmjprsglepvjmfttnxgawahjpnoglhslcnvvjravlnhmliogcwzoqtcivbdskmyiljietzxogmocdezogupagiktfljtaiakyaxkmbsmwgflfbsintbmebnksmhnfobnkpulyakgqustjgleykcdojqmzeedemempajdroujomzbgoscpjmuwasvfruquzcppvaueiyboigqtzwkzqdexktqflqwrpjyhkrtetiazykrsfwferhiwomwkhlkybqifukajezjxkztyxnpebevqfakzufdyuduzkevtzqvdvuxssckkhkujqotipgebddikjrujtblrlzvlcvxqjmvuyawrhltfvurxdayadgfiwlzkqumhprqbxsfaolohtivbcuuhpidwpqxfoyeinthxcskzyuqkfgnrpfxcjqioycbgjgytyyrrpokbcytdftycocnanqehxxsuifxlfarspwipwaibiregrumnsvxvmqvggpyxhglhpnqjuystfhximeobcflqbccngwtyiubbkppwismrpoezpiwcjtkfoeptubigkwsopqfljoijrwuvisokzmiqhklssyjooodjxxhwaevmlvjfieujkhirtndatawsuryyvaeatfptbusqomhbelmttmczojruizfqaqqnkulfcwhbaheddvqlhrfozmpyjrvzwfmahvonisadhtgrhpacmkltaazdnxciwymkjhjtliswyfjomosqfauqfcfsyzfjcaiaearquflgjxnqsfbvvguetirocslygcyrwxortwpobebhvgoeeevrtspjohmuvwzohsnpnvrhhpzonuoanyiewjhrqpznfkgfwrvmzvbvswuuwzwoidrfqhqthslmjslsmzmymyedcolrgdbnsyvezbwdxmxcuyzcvowhdhjzmajwsunvoxnqetxbdiqnkesvdnlqkrcomwzhwdnsqgrkjpbxevfsfugewvzrcmqrmlysvajrhswtofjowyalxjuueqoypecdafqfgjkkffmebbveqhjupdrwqfxahvvidyseowovmslybwatdcsdzybejpknagunxaphrovrspmejukrdxljcolxddltgafmpbvmgbmgtdnnwvnyngpbbbesvhejapswejgcfewzwahodvajbsdqxhutbxbiffgotlpslmbznhkrspdtwqkawzdntdjilqodbjhugqmlvmvblrkptpvxxzkehxxvhenezkgkkgowiakaymrvzlxifindnlbnkinokyongoevjwsvurzizwfxempomriyxcoyxnwlnxdzzdrqujiqzfnxpotpbjeopuilncikrnyzfmkndrvfpiueyizyzrualfqvlzefojoekirhvxalcknqxhbnhtmdeyxmhstyjrwcmouvzwqglmzwgxvuxypscphknnnmrykvhjbkwcpdczsfymcljldhfklyvvqzypxyefgkcmstflsgfbhkshbgwejrqfujlopanomsvndbpsqwmibafkwtvtalpbaudnsbffxbcewjnsfovqtgdlepaphyswoxhjwazxixjafhfrvkwscszvtrjonjydacvqjfwwltvubstnaarglimuywjdjkitenalicajijropnnnwogjwtlpfphiilszunbwuapfqhazoqtztiddgmslttpomsqgstgvnuljpvzlglvdcpqktfhxemcpmptpdcsxazbwcrqmqbrmumuxwnwhouvpiluupcdmujnwjfuuycpdecoxaqqppmbmwyguysfhkaoyiobpqiofxtkcqgcyeaogssxoirbtvdjrggxfjkpfewxwjseiyyznjilvjdafmnmhhtnfsgcuvlyskxzoviqboqykvpjblipzbqbderfmjugenjfzyvbcqxsubloxqrvpupxujieqfonqlcqqesscbnpegzwxnqmatmarkeaqufxkhbzmtyilgabxrhxpdasiglemhuzvtonueomezjpdbxvjirjtvrqheiihxjhqolrlvtlrqybuhmcysyfeyflmjgstvutghamzvqfwpcqqyuyktwsnnutaltaxyouvjmfkhguulwalbzpckualiinsmbfzhqhrupzdogdagosbincefjnqnitopvgdtmuiunktbcmdrpuhmhqbtpcreojhfbycndgaajpebvnhuogoxsdwzmrldkrfxvokpupkuqqrbrnytykimfpavpnpseezhkwbarkjhzadljxbsrkdbcfuknvzsbnhyrcxmokhlaagujyiqaxtdsykpcycfolazjsvvhbpbxsvznhyeaxyavbtzalbgfhmvjgxgvwixuabdqccszfkgshecqnjkcorpohyqsfnvkziymohikuxakwxdkooxseifcsbdtxinjovqrfqlnprrcdjmgxebxlttpmsduyqfrixnjaqhllbjjvoqfkklvblynnfnvqnkzffxgvrqyfwxjrvreaqdifvtgkwhueowqpnmzibkdkkkgdgjdgpgmcnrqxocdnzqechhilkjajmrorcdraopquaytfwjcdxxucepifvxajiamuhjryfmxjovldfgdrupdandtpyohtbkxotorygdeasgdvfxtuncsbfeexfilvylnsraguogndyibmhsfcuyntyawhcmoefbvywlabsdbjqjekbkgawmgtkmodtbmcwewsjxbaqmymdkvrcgyxorxvtoegofwtqtsquzvqamjjvlbkosttnolxrztqvbksqrcnxsiqoepvdhsmewqewvwaiyfntlewbjudavlnnlluqlgeehdvvllccfynuelllolnwypbckbjdhdaruzfqziuamfhsjfkejaiknvxbuewfjcxaydxedbjngdxyyjwleajylqgwtbjdyrbjgirqriisuudajiffxcoclojytvwlnxukimrlvtjtbduocerewoveupoaarjywitscbxvpdbxffspogxqggabesxwwawnltaviacsmqmizpxlugwvvrmdjymwixjuwbnfbydapwgvffvvsgtqhppctgdrjmozqvjkxgxaqfjsakvbljntcwxahjuqjxyexziourkrmhjxuvmgexugngkbqtjutrdbbcdcbfaflazkretsmqqteoprwugdlotfqeycxldghrmpnodhfqgznedgvigpgemcjiiotasxhllzcnvmrkajpstsfaesnrkcsgxcxcsewougyxbehwtpkeieniemiczcthlldqywiaqxohzxscicgkfroutneyrvsrdknyyjehrumjiutxapsrcvrabwwilrhqgbxyqdyslwshiyxcrqorolivwwlhbbtyvswpvmtwvsvrqjnjsfxybklxiwjwamljjybcyiceednkjetanucericwmahrclorqdkkaoeuwdqqhndueuojxurqfhuudiqsqvnfzmxptekyxpnxvokmyunnclggneyefglhkuuhdeeefipcqrjjychtnhnvfyadmkvdeqcaehmbbjgrueshuzrxhylddohlnsotjotinznmrhknlcszxlsyxzkywyghettwrishgpdycybvpxgpygkqyyiincqexmjmzcsapicdnbqyytjzqtkhguevgmarogdizvesfpvvoevtdymogdxvktxqrhewekiamnytohnaiaxdffevesnhjakbisoxometvchjuhugonifbtnzuztvnzrhxpzmabjoevawgktjfgkaxcezygyboelvpenlpknkrgagtlzokxiccgoxdkrncpgwxttuxamuonfmyntlytbitkzxaoufgnpgaghkasgmjtmzmmeqccguphcdkiqygtsehaymnpjeugtayrhbcxaayldchcflrknjernblilklgvtobmqufbkmzrzlozhmlpwigcwsbjhaqhfsjjgxbyzeacizonxqmfmkkpzdcogkbkdoprqnksksoywyrstkbdxmmqybdzlhmmgcqsvufzfjdqfsgvjimdyfqzbzuekbvvyiwyphhibmvjywfauhdqnxawuewurviptnrhivnqxcrgagrzsuxezrlwnlvwtfcqvnscuibxjuuhxioluqhrbjmghcurbqdkpftyuyxsmhiohblqvjbyofalkfngoruxzqqrviyzgqjdlvzynkpglpkgikxggfpmfmtqdpfzseibyvepbttwwgofvicvfsyydnuqmojlumpdfesocfaazdyudabclkolwsgfnmkamzsdqfofnzhffnnliawwviywmzgvhjehcdjgzejglobvuuhydllmtxaddojusclvldwfzpbvooapgttzuopussqzfqexkuevtczjxonbyxqjwlypkhkaqpijacsyzcxwewajalvqdhtpzbnsqmfpispibihuurzygikyphevkvduutnxfvvviwduxtmcbxtdrycbfnnftpzgeortbegdmitgibnvzsblcpuepadjvdmrzdbdurzwvjkuuzgebfgjmbcqthkvytotcapmhrbpkxumcyrmprgayicmmvjdqhhkmhxpnmhpculbgvctvusoqmyawzazrmovtumbivvovwihryptrpuzclmulomfwyfejzpnnepfgoslxkjlhgtrsynvbotwdavedrseyhkznubjiwbxaowzyalthgfwmcsloowwabzyldurmvoajuqjkffvxjvhicqfanemlzugijnpsgslsuohkkondyupkyuuzldzzwevwdapoicphfvprjcpskfpfonpiqpshdsehdykylmqvspblffabnpvieswwynttfrgqofilgpanxonsxgnobxlhoiowbyemxsotjmzpelbbzlwzyxlmilfmummtxcsftlvboyeqycciihlrnngoenwbxofrwgvsjofxjrkbnslxbtgpazhcnktbfldvprrillwavniuppyemasayjexsgwwnakvupfnwpceiaatgvnkudhttyjjofavszrmekfwpmlgkfbqwalbubbsjvzflhhsadtlcoqzuuriwxzqpwrbqypqforexuadmytjxmtnfxzwpsmrtpsacdxtjadrwmqtaptxteawoopomqgrqvrtiywhbvyzngivgtvgakiykprpqxmgkkqjzlrsnkenwdoqlwzrlcnxyhieeshtanezgrgpbhulxvczmowtbkjkmglbdkkyafulggmbdzgsfcmystmbastjxpkmceukjtmfpovrtlrsgndxfvncmgwzfreewddomihdknqqjngvqqqemjxiownjcqaruyivxpxcqrrssstjlgauocbcbdduozyxjtgitagwsvdcelplrheqnybcppybzerwpcxfjzuckiiizzxrsdoysmqqpezpigmqqnbwaitzbtjeteqgjfegcqxzzazcntofpxpikmdhenjcftuqbcjkvcrjadnzjhjfvjvctqqxdtcpyyijvnqqqbrftrwsnubfvnbjlewhvckzqyrmojktagirukjekrpspcfhwwjmuzunybgoapsadieavidppwrlatpkmnzybtzqnxkvqtkfmyqsiaqtpmusaeddobhsxeovxojruobclzucxasytjurqcssceyoeaycadbycufhtwwbuavgzahrantgftsnnszgtrcnfzpypqyvhcruaotqnjqqosxgioemhgnbcjjgwmpymatsosnqruzrxqjsironihumuplpihhemhuozoczetiwlxufqkccleysltlexrescybhtpwaplmwgyndqflscodcwdccjhzdfktnjkolhicznaxvtnqackwozkxrmdhlfajwifesfjnihgxsvgtunnlnuxoxrmnsfuufgdyhnkocaayokugrlxxzlrwdwhirwoaphpoimzepkahsblkfkoqmsvkedemzdgikddjtkryreocoseugnutganjijwiozyrjxlhdzjabzreedrrnofivbavvxjmdoytmbuigofkkewbhfdysmjjktnwuhmjeemfvuerxxzmxfqzlqtaclylizycuhwzkebpvoinbiqmrefxcunwtcexckktqkdgidozuierywxreobxtcffrmlewvagqbgiejlshesqiyafrkdcmulfibcxdfvqrbdrwxwtcwhlhwsebkphfwlfysbsvetbzpozkqyfixqxloxmfuinoqtnczvxozdsazrgaxpnqsempuptckbttjcrpcrilxinernykqhxbjqdgxxodhahoiefmgaipalvrayxtenjisrpvbrzmflvtzcvlfvcotbkovpzkzntgqpuhikglkyjsebaeyqvjwrgmtiboqzjlhlmwvlclohprbfmobfhecrwoqrrkzckzppmgbteqgzzqddggybpdzjgcdypmscexuwuvdkrqbbntckdbwgeijmwfvsjwcaheenqlrijclbqpazmwmqqwmipmvckxeolkmbcbrylblwqlmtkvvufofhfvujkqlbnkxqbiedncxjsxsvvquveoisxirlewwxcziiiarvghhfkkrekrilixxrsdvodjxtdzrsujlwxzcticnpbmixbqnxfpnhqmjfwktjedqmsjymvvwyruqzlsdwushjpilwjogaorrvjjjcvisabbhecscufxugkldjjpqexbnqrvergttmsvdapsjwstctuwmatyjxgsjjyxcifgugcipurvuodinrcfospfuertbtcaxhuadjkqyzkjhdzjlxrfsfaloeizxmrnngbhdftqminhqzbqrhujfgiopsalkdgakxrpunfvqzkzrzlcjqhnpowodvztdhcjimujathhidzwxmjwkatxfvmiftkksdtusuadetydlzrbmaolyzzhcmjjfuupplofkcrlrrqhcocpftmbtqmpjgntiuhsnkvsxarauajaruwhxsayamrdonedeaptjrlbtzwxzhfxslcnctwfabtyzhczcbazzjlcqujrswmuqhogxbqaudjyikxbraqetallwmfdkctzwtezenddupwalyrcuujphgtngonrgmgzoyuxtmtwcxrtdmpztvbiwvksydlmbvoeuwluvmpexctypecbgrgtuhempbewzywybwqnpfaliotlzmnozznyhftbqhzreuthcttzitzoxxzjwmjdrutkfenpdauuyguuaqwqwgaulhtzjeurnhrjvurcvksypqelmpurseesizpoedfwvepwmjlqppoccbstqgdarfcovpjiyzllrcoiampmqohbbrotscenjcaovztrzjvkwtyhscfqusriyoxvktmyefrydpvvgrvjxvghmtqspaccmejbxxfrcarxllowtrrebztscggqtwmdeaypnoygtqksfbbkecxhqyygwvccrfrbodprblrwukotemqvngakaurchlwkcagdmmalipkgmcvtdrmxdblyfbgjlgrjqwfmljokqturpmtwvxzozowrlxasxavinovkkamrxnmnkfyawwysdkpnbrmzckvdxwbafubtacbvtdbfjuzxlfyjdtsulwjazrorrvqetwalnaslekrpbzsfbcoubmxdlklrxdqsbhfledyysxltdmrcjlgjkwkhlprjjmpkvjjjyvyewtbapjihicubkwsbkdvjncfdpmafessnkkjvqagxuxmhzvsakboyxhuhctcgfbcnphbtbzdoqwfxlztmqkewesywfxudkmofhutlhfgvzdstvqkwblavclceotiqqwqjcghouwpxtqktrvltvhwigucgxyopllsrwutarxzpbcnilmpbxgqsdqozipmeizqwufeitmriytayqkbzlfnthbgzjtlmnjipyrrbtrbdjtcqimtwuqgwipaofjcuzgabgizjefijqmwcouhnwfloabbipaiwxbztyztddddeovtfxajgqfrozgagvllyawitvmhwixaerhggmsqdzexyqhotkhxrnprjobxxapapojjvwqozhjjiadvmlzbypcjpnkdweugxnnwesgduhqmnmkxifknlgkzilndscapjakhuezsomdgcidtncewiefqtefzfrsqiwoggndcgmbplmeylwoskvxahrbltljypsqtesotgtndfyvmjmfakxekxticuyvdvcgpqqqoqerlspnvynpzbkjsuetpharepourtefaxcmdkahshffzamkkxiviojyfbgdyjfbakejycxhsmjvhwstixyygqegvtmmdjilneprzspuzhvalugdzgzpcrtqvpmvdozosolphdwewmwgvpnrmxjgmqlomfbwumxvcymfzxmikuxadnoibptfpsdzjecfrhllmsjajevlphqtmrmzjnxaxidnwihqofvmdckswfwqqwipjojkughzujgyuqfqhrpsrsbqogcswctshpfxzmpgjggoqfvggxacmiaxrccujorvigopkweebyjecxptuqzvxyinzcularbkpavokjtenaxvbpqergqaicjavkiqoszkwvmzzvnskdbnwxaukoflqqkrzakmjqpsqnqsbyeathgyekwpntimuhdixjvcqeedwbufltsckufbwqbwoepnrvbvqtjybjmrfjdljfdyjxxrxvgroqdzcmvgnpctqvewxskqdejvfqplgpqnvrwiqxsymggwqyclyxaqcfegakxkyiwvhbbaruwmrpekvxgqqtvoqseutevjrxlpbjpucmmsiuonmgsyrfvkyhmfbndmncdejqeaptkoyltndbuviabpszknicjatmwobcveedhigjvgupludjtsvsmxrdicihnlnxsjfucqsdwlnqwaukxlvxfavuboalxpztlcatximxxszmgqgqznsyxuacweepbdphrvxnlfdjfazjvffwyrhfanfvcecekjtowxaxmnalwuwcxrkepjlcprrrkchgyrroqzagjoirugjhiidhqsnqeceafdfwiwznbvhsftdhnttdzlwpuyjkhkzshkosgweybsropyqbpfmkpasqzpsuwkueqjqczybjfycnnqtxatlstchytdueldaouduwvltauitpplcxtakngwpulgfupmgliesmctndxkapqwblypntcsvadbntbythfmlewvlqzjtmepmmlnstmpqiabzpemnxzozdfbuphlsrajyckznlvglmmmsyjrjyupvrnpwmttkrhkiyftpgrrewjdcwesrswumyimhxjwlkynabiyldxrdlwdvsqyyzyokvxeaykjqsovjysmcaourtoirlbfrsodkxkfbiwvzmcwconsqunqzchfczathndbjxygiqumlyyiygoetgukzchzcioqpmhmcahpozhenanedtiygoeauipgfiwgvskfybtumnhfnysrovbrrciyxzmshajyfjuoussugavjzvkiziotyfkndkxyyvzvxryimglthavhclqkqqlqexziohasoxnnbazfvhljgxkeonhnkoajuchsothuvmmgfgijsxqluudjmjrvjlkdmvmunmxdojfhdqawefoypxxjslvobppigggnvuvqqhvztxlnuifqqkwzwhpskcshtzblammotidlwrgnzzekjszblvcudtaadbqfqbwnyokvsalttjqhsrsubggcewxjpfygzrtrbdlpwxkmctdftimxykqxpzksvvkeydatiqlqqvkansgtswyzcqnfpysknknynifpdccxbklajdjjaqxihshvdagpmnsrrswfveghwundajjxfoksuxvjbolcfnkazivfqfzjpvoyvizvabrvcapofuunlardxridvykolimnqbdhzcliqennblmuldtplatkkvhwzwmiygxrwkgpnzydqyiqfahdgmqezgtvmpamboxyettiiulpmaupxdxeoujvqosalskltxpmhixgohqrcwytggpiydzxbmqdufzjgbuovrbedinzkoyokrbgcdfxbeskacnpymdchydwybdrxpypjwwopdwwjnrtyvcfmtjgrtuwkzgqlgpeupectaplzxshkkqtkwakopiytkbauekfgrcdujrylsjuljompwcbjmopnjoyixvbtxtzwktkngijwvxhynxhfciuhwkajtzkotnnixefqpggxdfhtnniwltacorrejxdtxmnjubxegvwampuicvxubnleuowtnrwazcgzbqzxdsirdoqpyfnvkloryoixggqxardrzzpxhrzgxvognftttmmzzkspythvtdwdkqnlfxbjvmawpyqorrgogmgujsctibzndqtjgvgrdrseopbgdtmbrllshuxbvxgunnthndbthldxtlwgqfxjeujwfhthiashboorujngjvqbcvxevkpapimdawvjcxuutpdockxqciinrtozbnpwqyvemrjwkhkprpcnakhtfxwvgbqsaigjlvttzmxojrsbdlwjbqhvtifxcylilgzuoucgqotiqmvagzqpjddnnnirknkkqhnimacemqpdmgmesnloqxzmpwasjozwfrrbkbbstwtazvuqjhkvncggllmviyxpsxchepjhpareoqdultutzsytqqmqbfqvmnphqnrpmcngcfjrvixoxpiadpmzrhwulzxofbhgavhhaihchlkgwhhkneqxjhcoiuungxdhxvvuwqgcwbdyjvjwfdvgoibupwofpymmlsscmqjflvpyyilrchnecoaxtbrtfckrtekfmnyjoddmratwsowgghflvawyzxbrznmdetwbmgudvgevbxiciktyfefxkhqsvigervcivwvgvppepkycpugymjxldavzuioqsncnfxrmrnuwjzidrcjcjwdqvistnadiyjbjxqlnnohmhvcnpyygeagspjhmpksspyljibujfvzkiesxirkrotulkjnxsufkwudnrhikhtjptyrhhqkqwlmhcnihfswdonirnxrowsawfrptroglciqlmvjoskyntlreordkagbkgftyttfwzrasjyfwdfgnhhsrklajoxwvetaatsklxewccozduhuwohgraagqtxufpksndklkhmfpxrchwtgxetkbzpnziwpxftjqncmtegfquywkvkgifsaimyiwiishdardeqlywogvawntkonnhbgmzcusgxfsubqlwgzqfvhkqzhcfbxpeaayqotabtjtfzibzujvmixhafmigzktaagtztzxiumnyocpnbdbsulyncwixyehveryrguwthgmzkrgdlxhozacwjibzixzmqkyycywoinuqblgzmfkojuagnliofhtnfafgwzsyjwbkxetikppvfaykyxbyefgzxawimbudwmrzuxyfqkaqwkcikjwravuytuaizdsgebhvzstpwvyroxvqhbptioskgvwxplohhqqllocrggtmwgfkpaysrjthovqxbdoavkjzreswfasgrvimnahziyqtztypblupnwnpgeosdlmstefruolctaraezcspqyiquabjhyluqqkvdvkxoiyezecgvkhkdninkotaruhcczgeqajjxebjeulnciujjbjvauhsjxsbdnzasgwmmwlmthypgwysyzvjqclfrdghlcjnnrzdksxorazpagppwigxpmlkpomwhfnxbwtxqbuugkifjuyiwwgtpqrbkohjvqfdqpmngmpjfisgtzuqvajtaydkwogvhhgtbjpjlokbrhcoawfbgowpgjfdlptjqflidmppkrdwkdcrlvillsvnjznonnjcmpwvlebdfmnyvkskxhrpitgjfvrowqbqmviborwwdfqrjzccusibkshdasovgjcvwyjtoediwsafbvkleopazygyzjutdbuswazeqanbjquihzjpgxiokpzhiigkfrzuoakujijdnmowcptkihjwfsvxotgdwnpsqnlxyccbtyvchsgliqgvfyewxrllnvzpqcossucjgbosjuogflvvzsibrsjxeiohmyfonmakcjwoebmastdlmjoirhxeqxpmtuqalbwcxsraxssrnfdcopsthcyknmjetrkxgktwecotfvfdghhxqdkxnbtxgfvlusjsbjplhpgzthndrwtfnjrfzgefxnzdcvafkkzliapjedniduheggeoveiadsibqdncutmbhejlejvojbpikkpybiiyxermybelndphobodlrrltfalfhzxolxvprevmebrucducvaotcsnumomhqsmrwspyrgvxtzdvzjxfnsfsgqjlwaaopjjqbpymojrhfvzzidkrwdvkfhemkgjxiytbqezpcznmuqfazudoemkbahkwtenobhxrtpbagomaxxprjbyihmlfvgwnukibyrzlyurzqtxzjyygofnmdwiusxdvpuvsjlfdgsckqntwdxorvaezpmyaqpdheeqaxttrejvimsdutszcrxvfltvxqyzsaxsmwyolqqaewcqidcccvxnvokiuesruuwshzfxzrcnmjenwdybmssquplophrpoklpctztyyewwecyozqxbwinwrpvibkceivraggizjxarfengcdovwhjdhdzopnmeasmdolkuipeuewfybucqiqpqyayeddvedhqnogqlkekgjczrwyjbgidrkemjxowbsjvvvgisxpqanvpaxbsouwrbudztjkvtghhlflijrwijolgmtcmilhecdrvuveswevlyolowxqxwdbakqgsrwnthpvmujrijajxgyuitwqfabtkinkymezsevswmjuxkgjhcvskggysyraehlcwtvvenoufezdnmxenmqjgktuqlgadzdabnmcuvdhczmsnajterleudpqkgqrhurhujdwzevugyyikwrleuvhxzaiwbqlqytttlzmlzrsnezqvmnzhknwyzbuggdmhjvxnkfflodztoodanvwsxtbxbeiqxkbisxxqdgzbubflousgeqwrvlslqlzfeebcajefgtzzevhnmfnnjefgqqonmfsufhytiwqqhsinohzcjbysdmkmcgivpwqcpqyuweenayiokvrfvfqaxmwgptdgowmdmyuaviivkqdrtwkcojplnkfodvozevvsrjsewnkinaqqpxjwxykckmftmvbcwepcsyocgqrywizfcryogidjzdmdxkbkxktqbpgfstxmqxgbvrqkoqldobxqoiaceszbdkpqmlgzgwrjbuubnvuogpxiauemndztujybxmgdjwpnhcauhehnmanllbkontgynwmoxdmawsnnfvuvvfsfixuvjshwowybfarjsvjfidatgrxxqmojvvjgtorhspyaxkptnixggbixbpchlzjoxiucfyhvynjyqyiyqazulirgvpddkgttiidgvafmhthgznllpuvczaovxifmjgoqmltwonsmszitrspkeersowpskebvvlzdsbagbfslkzqcunmtciaftakrkpieyxwlvtpmxhvbbqzmacivuwcuuwsrnfoymnhhmpltgzlqepyguohggmlfhqtaqmgwkfjauxgyvzowpzgxodbobyvpvmenjlrmufrzijkdbjyanzswpfhotutyeapduyguipfpjxvfrhienrdkpaldjxogvvdxplocfriethpnfljpsledtqnbgshhyabrbiueznvapxapqwfvasecyvsfyazamzvqfuaflxyjeodishqmvspaehhhwztmirqbzbcyhnkjlolxjfnlcfmqgmyudljjgzsnjvgmqaletnysfolbpmkofidnmgaogsazzncopnpquzgfcrqhiyzroqddsyudmwiftounqwbiiabzavzaavoutztndyuelvokzdxdsaeckngbdolwggleorprjijlkvredkyadozctgvktybejatrguyewsrgocollzrzaipouyxqikvrzafujvzhjwrxqvwqdmgajwiqgcklorgcgztcvneyjfnzkafmzidwdtroyxdrgwjsvkrkuxrqzbxqxyvjzddixxpcaamhyzngdeyphamutdwciehwtqjysoqwkwhqulingobnxozteuntrrlbhgjqwotfcrmnsujsysmhhovdwvahmisntpesdffpxncfyviumpcpuwvfyfbdcyzdgnbtextcyqtlxxyxspwyhlpicofbqrchspzmpvmegnxotlmqralvudlftprdgsutbrzjomwpvpyjbrojubhdrjhefittjkmgpbjsikyywdqbcddgviirulkekwzcypxqvgmbfhtmtqvgccpyshlgboajnxprxgpxbyfreqwfzamuvkflbpuchywikvsmtkzqbongqvbqowasykbikryaexgyfixpdczkrekbnbmvjrmlguxefmzuegxhtuugpbaaikmzwfydeufgtkwwkfhrbtfywkaxmgotgftxhoxydlkwmgiuxskrbrlwcgnwbxnxxvtqvviemiignpxyuwflcmphiwojqhcbmyoafpmckuevzmhbjdxsjjnmzasfmsnmwbykfzcsvjsypqxecgceioafuynmndomuqwcwkhysokojirasamyjgmhdwvqfiayqfucomcbrpdrzthbfwejyszhaikkqynxynohojprjbqszvrtqjeiwlesslbtulvfwpksiwkokbsqmsjweezmmlnfmftwqdivfyvqyieyoweudrqpypiflljnscdguyohhkphnvhhnsauafaeholwergmkzvmqckzexqovuhmdozqqtcebymlfptnsrjswxacdokvcsyxxdzszdxouimlnjkvexucnvdxzudpgirvjuuftcggyrnlgdgyhvdroeakwmsmoprjxowpjjzqqfobyprxjxvdbwtfzizshdgmkocixzcawexjrssxmxtxgwlplilkwngoonthemdqgckhhbyfwdxnhpzwutrtgjsedwztkjaqdaxkudkjvrqueoimpoojulcymixtjrxghhdgtsyzgybkbmnbwiohzlhjnfioelkmnlisrscjmtdyrskmvdpocwuunhizxyutwuvibrwuvfaxysrzrwzotjxnqnktgpkhmydaejbzcpkwntbjdfqmsicosldhfbneqocfgkkdagljuevnsrcqaadweocotytpsvsilmyuebwclgqgyfxkdqmdngrbpubzdxopnjdxjqwctgumfmugbhhanghaedmuxqebzmqqytbvnodxviebajtmpjryogbtucxkzahbmjmzmcxullpjlseerkzdxsbfgezplgnqzvnfemfrhjhhahzvtsqgsxlzzjbbhvxvztsasgxoyqihmzqzqmrtlzqmdtoaretzfcrexjckjhcaalparqlfzqhphuaecgxcqatxdnyffashpimdnsodioisaegkwjqaxydpgcrlnreruzpskdewhsqfrsimnhqrfgauooopxymhajfoapfwyityxtmqfakmgaxcwkjkchkvgzwwbfmuxxxqhwdchtpozqvubknatukfosqfgpbmemehwejfevlxfxdoxrxomsktidtlnlkfzymcivmunejzzrgftczrpqnvcperxolrbkdqrsurclumoibujfmidrhxmndnoaysodhrvrvzomqjjuyfuzryzwlignyywnajbstxzqfpihzzarittypmzwfolrczchmoroirejfukwcskyhjknbyktxiybwrqvudhwhcljsdmjazzczvztdxdgofwcseczmxspfcreszarntjluqhjzdpvwbmtszmyzefcdootrpkyjpzwcmptwzjmbcuuhapydweroaxsqaihtzyukgqxxxamyzsqjiiypvrhqgnvldwrrlujvskmhkmikytokyezbmxxjgxubkiizxshumknhodmywfeiprksqsjmevwiuhlnctkvephcyvnazuicnaoxgywbvwegvrhwspklqrgyaofxuapircbeelxdkktwyvymvcpurmubewflbpqekadlzdfzickkrsdjfxhwbdrrgzlrjcbzneutaejhlfunbslgbnhzftmjcqoojcivfuzcsenglxtczhnsosgxsnilhshwhqbswjczfbkmaybbxoldtibfljhrligsqzjpipieaxaeflqpucnyyzmjinjjmaxunbhyningtwmxnibcolxfvjxsefzamyiulpajbigdckcrkrgfvqgolxlawpoozvddzmmlbfisboqivotmkbjindduqkfpsuazbpfloshazyxgymrtclboerzdjojojlxtztrtkebspdxqbmrxjedsfbhhhhjmxcohdnhxsrscbyxckifonmbjfkfpgmwsxyaegvldfeemmpkpvjghgpvvwipbmfwgpbdkmitcmpbrwsgixfjsqtlkggyocmrlgjcwriwwpearagbcsogrljxsdsegpcriojutwivrcldjspzuzxpkhagwfflgqefjqdeqisddokmillysnvoppngraunkqssgpnkguqregyqmoeszhzmmexthuytquckjupyzpoaenojkzuwzbhyysgklgkgvpepqsywpcnvjlseydgugulrkjucvcwubeqrcavdlfutkpchwjlwfsreocwrfshmwzrzxnkhrnfaewmzenxyvdpajmkxdorvixupjzqmnwsjwizmyirwyxtcargnrmqwgxvwmmudcxbmjwbgtbfocqyhnbmmoifjgohithwpsilbhxpapwxnulitskhivwvkjwcsbmybkxcmwulrbrabhjqpuuggvdvuttvkcwsxliyfhihzplbhlinyhgdzvagqwbjaotvcaqqlpyzqjtvncikbuezanoihumpumscsudrynqdvmdcwnfmuguloptvfgdzbbiatnfvrwclzvedbwonorjijjjjvsseidanxiwoluxnmanfiuqvoffutagqssijqmfokvizzijsyecfkcqjduvtrubbubcpvwxfvefprdcofhgtcckjfoefeswvclzfajdqlzgkoeambhoazlpgatwzlsyilckesnehkwlsulcerakrjwjxvidjeaulztzfdltfxyagsezanqtrwnhvqwkafzlkkbtkfadbcmvyzgwznkkazegbijhzyvcowagwrerqclzassyrtxlupttukgxyiimxdgdqdkgnuymaijnuumdzmzpfvhtnrtgijbbrtsgffvztoywsxsarlinxfqvcmrkhzzkiboiouiqmqtqzjxxgynccdkdolnfgcsmwydkzwzmpzxjqmrwhpegtmqbvibmrxmthyybptvjqad"));
        System.out.println(new Solution().maxRepOpt2("aaaaaaa"));
        System.out.println(new Solution().maxRepOpt2("aaabbaaa"));
        System.out.println(new Solution().maxRepOpt2("abcdef"));
    }

}
